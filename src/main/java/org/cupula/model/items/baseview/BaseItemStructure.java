package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.items.ItemTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import org.cupula.model.EntityClass;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "base_item_structure")
public class BaseItemStructure extends EntityClass {

    @ManyToOne
    @JoinColumn(name = "base_item_structure_id")
    private ItemTipo item;
    private Long possibilidade;

    @OneToMany
    @JoinColumn(name = "base_item_structure_id")
    private List<BaseItemItemStructurePart> parts;


    public ItemTipo getItem() {
        return item;
    }
    public void setItem(ItemTipo item) {
        this.item = item;
    }
    public Long getPossibilidade() {
        return possibilidade;
    }
    public void setPossibilidade(Long possibilidade) {
        this.possibilidade = possibilidade;
    }
    public List<BaseItemItemStructurePart> getParts() {
        return parts;
    }
    public void setParts(List<BaseItemItemStructurePart> parts) {
        this.parts = parts;
    }

}
