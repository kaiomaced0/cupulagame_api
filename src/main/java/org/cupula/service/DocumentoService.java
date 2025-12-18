package org.cupula.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.cupula.model.documento.Documento;
import org.cupula.model.documento.DocumentoTipo;
import org.cupula.repository.pessoa.DocumentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;


import io.netty.handler.codec.http.multipart.FileUpload;

@ApplicationScoped
public class DocumentoService {

    private static final Path BASE_DIR = Path.of("documentos");

    @Inject
    DocumentoRepository documentoRepository;

    @Transactional
    public Documento uploadDocumento(FileUpload fileUpload, DocumentoTipo tipo) {
        try {
            if (!Files.exists(BASE_DIR)) {
                Files.createDirectories(BASE_DIR);
            }

            String originalName = fileUpload.getFilename();
            
            // Extrair extensão do arquivo original
            String extensao = "";
            int lastDot = originalName.lastIndexOf('.');
            if (lastDot > 0 && lastDot < originalName.length() - 1) {
                extensao = originalName.substring(lastDot + 1).toLowerCase();
            }
            
            // Gerar nome hash com extensão preservada
            String hashName = UUID.randomUUID().toString();
            if (!extensao.isEmpty()) {
                hashName = hashName + "." + extensao;
            }
            
            Path target = BASE_DIR.resolve(hashName);

            Files.copy(fileUpload.getFile(), target, StandardCopyOption.REPLACE_EXISTING);

            Documento doc = new Documento();
            doc.setNomeArquivo(originalName);
            doc.setCaminho(target.toString());
            doc.setDocumentoTipo(tipo);
            doc.setTamanhoDoArquivo(Files.size(target));
            doc.setExtensao(extensao);

            documentoRepository.persist(doc);
            return doc;
        } catch (IOException e) {
            throw new WebApplicationException("Erro ao salvar documento", e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response downloadDocumento(Long id) {
        Documento doc = documentoRepository.findById(id);
        if (doc == null) {
            throw new WebApplicationException("Documento nao encontrado", Response.Status.NOT_FOUND);
        }

        Path path = Path.of(doc.getCaminho());
        if (!Files.exists(path)) {
            throw new WebApplicationException("Arquivo nao encontrado no servidor", Response.Status.INTERNAL_SERVER_ERROR);
        }

        StreamingOutput stream = out -> {
            Files.copy(path, out);
        };

        return Response.ok(stream)
                .header("Content-Disposition", "attachment; filename=\"" + doc.getNomeArquivo() + "\"")
                .build();
    }
}
