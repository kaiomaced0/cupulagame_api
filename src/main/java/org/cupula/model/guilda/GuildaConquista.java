package org.cupula.model.guilda;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;

@Entity
public class GuildaConquista extends EntityClass {
    private String nome;
    private String descricao;
    private String iconePath;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIconePath() {
        return iconePath;
    }

    public void setIconePath(String iconePath) {
        this.iconePath = iconePath;
    }

    
}
