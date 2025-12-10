package org.cupula.model.items;

import java.util.List;

import org.cupula.model.items.enums.ItemTipoTag;
import org.cupula.model.structures.ItemStructure;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ItemTipo extends EntityClass {

    private String nome;
    private String descricao;

    private Long limiteQuantidade;
    private Long pesoPorQuantidade;

    private ItemStructure itemStructureBase;
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
    public ItemStructure getItemStructureBase() {
        return itemStructureBase;
    }
    public void setItemStructureBase(ItemStructure itemStructureBase) {
        this.itemStructureBase = itemStructureBase;
    }
    public List<ItemTipoTag> getTagsBase() {
        return tagsBase;
    }
    public void setTagsBase(List<ItemTipoTag> tagsBase) {
        this.tagsBase = tagsBase;
    }

}
