package org.cupula.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.cupula.dto.texture.request.AtualizarTextureRequest;
import org.cupula.dto.texture.request.CriarTextureRequest;
import org.cupula.dto.texture.response.TextureResponse;
import org.cupula.model.structures.view.Texture;
import org.cupula.repository.structures.view.TextureRepository;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/textures")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TextureResource {

    @Inject
    TextureRepository textureRepository;

    private static final String UPLOAD_DIR = "uploads/textures/";

    /**
     * Lista todas as texturas
     */
    @GET
    @RolesAllowed({"ADMIN"})
    public Response listarTodas() {
        List<Texture> texturas = textureRepository.listAll();
        List<TextureResponse> response = texturas.stream()
                .map(TextureResponse::from)
                .collect(Collectors.toList());
        return Response.ok(response).build();
    }

    /**
     * Busca uma textura por ID
     */
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response buscarPorId(@PathParam("id") Long id) {
        Texture texture = textureRepository.findById(id);
        if (texture == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Textura não encontrada").build();
        }
        return Response.ok(TextureResponse.from(texture)).build();
    }

    /**
     * Cria uma nova textura (sem arquivo ainda)
     */
    @POST
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response criar(CriarTextureRequest request) {
        Texture texture = new Texture();
        texture.setName(request.name());
        textureRepository.persist(texture);
        return Response.status(Response.Status.CREATED)
                .entity(TextureResponse.from(texture)).build();
    }

    /**
     * Upload de arquivo para uma textura existente
     */
    @POST
    @Path("/{id}/upload")
    @RolesAllowed({"ADMIN"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadArquivo(
            @PathParam("id") Long id,
            @RestForm("file") FileUpload file) {
        
        Texture texture = textureRepository.findById(id);
        if (texture == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Textura não encontrada").build();
        }

        try {
            // Criar diretório se não existir
            java.nio.file.Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Gerar nome único para o arquivo
            String originalFileName = file.fileName();
            String extension = "";
            int dotIndex = originalFileName.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = originalFileName.substring(dotIndex);
            }
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            String filePath = UPLOAD_DIR + uniqueFileName;

            // Salvar arquivo
            java.nio.file.Path targetPath = Paths.get(filePath);
            Files.copy(file.filePath(), targetPath);

            // Atualizar path na entidade
            texture.setPath(filePath);
            textureRepository.persist(texture);

            return Response.ok(TextureResponse.from(texture)).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao fazer upload do arquivo: " + e.getMessage()).build();
        }
    }

    /**
     * Download do arquivo de textura
     */
    @GET
    @Path("/{id}/download")
    @RolesAllowed({"ADMIN"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadArquivo(@PathParam("id") Long id) {
        Texture texture = textureRepository.findById(id);
        if (texture == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Textura não encontrada").build();
        }

        if (texture.getPath() == null || texture.getPath().isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Arquivo não encontrado para esta textura").build();
        }

        File file = new File(texture.getPath());
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Arquivo não encontrado no servidor").build();
        }

        return Response.ok(file)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .build();
    }

    /**
     * Atualiza uma textura (apenas nome)
     */
    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response atualizar(@PathParam("id") Long id, AtualizarTextureRequest request) {
        Texture texture = textureRepository.findById(id);
        if (texture == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Textura não encontrada").build();
        }

        texture.setName(request.name());
        textureRepository.persist(texture);
        return Response.ok(TextureResponse.from(texture)).build();
    }

    /**
     * Deleta uma textura e seu arquivo
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        Texture texture = textureRepository.findById(id);
        if (texture == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Textura não encontrada").build();
        }

        // Deletar arquivo se existir
        if (texture.getPath() != null && !texture.getPath().isEmpty()) {
            try {
                File file = new File(texture.getPath());
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                // Log mas não falha a operação
                System.err.println("Erro ao deletar arquivo: " + e.getMessage());
            }
        }

        textureRepository.delete(texture);
        return Response.noContent().build();
    }
}
