package org.cupula.model.containers.basecontainer;

import org.cupula.model.EntityClass;
import org.cupula.model.containers.enums.ContainerTipo;
import org.cupula.model.structures.StructureUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "base_container_structure_unit")
public class BaseContainerStructureUnit extends EntityClass {
    
    private String nome;
    
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private ContainerTipo containerTipo;
    
    @ManyToOne
    @JoinColumn(name = "structure_unit_id")
    private StructureUnit structureUnit;
    
    // Limites máximos permitidos para containers criados com esta base
    private Long capacidadeMaximaEspacoLimite;
    private Long pesoMaximoLimite;
    
    private Long possibilidade;
    
    private Boolean ativo;

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

    public ContainerTipo getContainerTipo() {
        return containerTipo;
    }

    public void setContainerTipo(ContainerTipo containerTipo) {
        this.containerTipo = containerTipo;
    }

    public StructureUnit getStructureUnit() {
        return structureUnit;
    }

    public void setStructureUnit(StructureUnit structureUnit) {
        this.structureUnit = structureUnit;
    }

    public Long getCapacidadeMaximaEspacoLimite() {
        return capacidadeMaximaEspacoLimite;
    }

    public void setCapacidadeMaximaEspacoLimite(Long capacidadeMaximaEspacoLimite) {
        this.capacidadeMaximaEspacoLimite = capacidadeMaximaEspacoLimite;
    }

    public Long getPesoMaximoLimite() {
        return pesoMaximoLimite;
    }

    public void setPesoMaximoLimite(Long pesoMaximoLimite) {
        this.pesoMaximoLimite = pesoMaximoLimite;
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
