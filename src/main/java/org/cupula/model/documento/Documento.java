package org.cupula.model.documento;

import org.cupula.model.EntityClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity(name = "documento")
public class Documento extends EntityClass {

    private String nomeArquivo;

    /**
     * Caminho físico ou lógico onde o arquivo foi salvo, incluindo o nome "hash" gerado.
     */
    @Column(nullable = false)
    private String caminho;

    @Enumerated(EnumType.STRING)
    private DocumentoTipo documentoTipo;

    /**
     * Tamanho do arquivo em bytes.
     */
    private Long tamanhoDoArquivo;

    /**
     * Extensão do arquivo (ex: "pdf", "jpg", "png").
     * Armazenada separadamente para facilitar operações e validações.
     */
    private String extensao;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public DocumentoTipo getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public Long getTamanhoDoArquivo() {
        return tamanhoDoArquivo;
    }

    public void setTamanhoDoArquivo(Long tamanhoDoArquivo) {
        this.tamanhoDoArquivo = tamanhoDoArquivo;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
}
