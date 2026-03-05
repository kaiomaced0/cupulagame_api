package org.cupula.model.structures.basestructure;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.Structure;
import org.cupula.model.structures.enums.StructureTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "base_structure_tipo")
public class BaseStructureTipo extends EntityClass {
    
    @Enumerated(EnumType.STRING)
    private StructureTipo structureTipo;
    
    @Enumerated(EnumType.STRING)
    private PlayerRaca playerRaca;
    
    @ManyToOne
    @JoinColumn(name = "structure_template_id")
    private Structure structureTemplate;
    
    private String nome;
    
    private String descricao;
    
    private Long possibilidade;
    
    private Boolean ativo;

    public StructureTipo getStructureTipo() {
        return structureTipo;
    }

    public void setStructureTipo(StructureTipo structureTipo) {
        this.structureTipo = structureTipo;
    }

    public PlayerRaca getPlayerRaca() {
        return playerRaca;
    }

    public void setPlayerRaca(PlayerRaca playerRaca) {
        this.playerRaca = playerRaca;
    }

    public Structure getStructureTemplate() {
        return structureTemplate;
    }

    public void setStructureTemplate(Structure structureTemplate) {
        this.structureTemplate = structureTemplate;
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

    public Long getPossibilidade() {
        return possibilidade;
    }

    public void setPossibilidade(Long possibilidade) {
        this.possibilidade = possibilidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
}
