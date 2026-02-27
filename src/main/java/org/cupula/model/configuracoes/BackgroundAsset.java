package org.cupula.model.configuracoes;

import org.cupula.model.EntityClass;
import org.cupula.model.documento.Documento;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "background_asset")
public class BackgroundAsset extends EntityClass {

    private String nome;

    @OneToOne
    @JoinColumn(name = "documento_id", nullable = false)
    private Documento documento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}
