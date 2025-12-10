package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.items.ItemTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class BaseItemStructure extends EntityClass {

    private ItemTipo item;
    private Long possibilidade;
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
