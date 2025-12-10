package org.cupula.model.guilda;

import java.util.List;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Guilda extends EntityClass {

    private String nome;
    private String descricao;

    private String tag;
    private Long nivel;
    private Long xp;
    private Long saldoBanco;

    @ManyToMany
    @JoinTable(
        name = "guilda_tag",
        joinColumns = @JoinColumn(name = "guilda_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<GuildaTag> tags;
    
    @ManyToMany
    @JoinTable(
        name = "guilda_conquista",
        joinColumns = @JoinColumn(name = "guilda_id"),
        inverseJoinColumns = @JoinColumn(name = "conquista_id")
    )
    private List<GuildaConquista> conquistas;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public Long getXp() {
        return xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }

    public Long getSaldoBanco() {
        return saldoBanco;
    }

    public void setSaldoBanco(Long saldoBanco) {
        this.saldoBanco = saldoBanco;
    }

    public List<GuildaTag> getTags() {
        return tags;
    }

    public void setTags(List<GuildaTag> tags) {
        this.tags = tags;
    }

    public List<GuildaConquista> getConquistas() {
        return conquistas;
    }

    public void setConquistas(List<GuildaConquista> conquistas) {
        this.conquistas = conquistas;
    }
    

    
}
