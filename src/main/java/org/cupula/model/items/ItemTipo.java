package org.cupula.model.items;

import java.util.List;

import org.cupula.model.items.enums.ItemTipoTag;
import org.cupula.model.structures.StructureUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.cupula.model.EntityClass;

@Entity(name = "item_tipo")
public class ItemTipo extends EntityClass {

    private String nome;
    private String descricao;

    private Long limiteQuantidade;
    private Long pesoPorQuantidade;

    @OneToOne
    @JoinColumn(name = "structure_unit_base_id")
    private StructureUnit structureUnitBase;
    
    private List<ItemTipoTag> tagsBase;
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
    public Long getLimiteQuantidade() {
        return limiteQuantidade;
    }
    public void setLimiteQuantidade(Long limiteQuantidade) {
        this.limiteQuantidade = limiteQuantidade;
    }
    public Long getPesoPorQuantidade() {
        return pesoPorQuantidade;
    }
    public void setPesoPorQuantidade(Long pesoPorQuantidade) {
        this.pesoPorQuantidade = pesoPorQuantidade;
    }
    public StructureUnit getStructureUnitBase() {
        return structureUnitBase;
    }
    public void setStructureUnitBase(StructureUnit structureUnitBase) {
        this.structureUnitBase = structureUnitBase;
    }
    public List<ItemTipoTag> getTagsBase() {
        return tagsBase;
    }
    public void setTagsBase(List<ItemTipoTag> tagsBase) {
        this.tagsBase = tagsBase;
    }

}
