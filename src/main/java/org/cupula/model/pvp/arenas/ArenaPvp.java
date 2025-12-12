package org.cupula.model.pvp.arenas;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.pvp.arenas.enums.ArenaPvpTipo;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class ArenaPvp extends EntityClass {
    
    @Enumerated(EnumType.STRING)
    private ArenaPvpTipo tipo;
    
    private String nome;
    private String descricao;
    private Boolean padrao;

    private Long x;
    private Long y;
    private Long z;

    @ManyToMany
    @JoinTable(
        name = "arena_pvp_estruturas"
        , joinColumns = @jakarta.persistence.JoinColumn(name = "arena_pvp_id")
        , inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "structure_id")
    )
    private List<Structure> estruturas;
    
    // Getters e Setters
    public ArenaPvpTipo getTipo() {
        return tipo;
    }

    public void setTipo(ArenaPvpTipo tipo) {
        this.tipo = tipo;
    }

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

    public Boolean getPadrao() {
        return padrao;
    }

    public void setPadrao(Boolean padrao) {
        this.padrao = padrao;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public List<Structure> getEstruturas() {
        return estruturas;
    }

    public void setEstruturas(List<Structure> estruturas) {
        this.estruturas = estruturas;
    }
}
