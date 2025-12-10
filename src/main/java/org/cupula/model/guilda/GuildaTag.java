package org.cupula.model.guilda;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;

@Entity
public class GuildaTag extends EntityClass {
    
    private String nome;
    private String descricao;
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
}
