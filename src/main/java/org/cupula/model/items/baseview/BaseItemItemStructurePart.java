package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.structures.ItemStructurePart;

import jakarta.persistence.Entity;

import org.cupula.model.EntityClass;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class BaseItemItemStructurePart extends EntityClass {

    @OneToOne
    @JoinColumn(name = "item_structure_part_id")
    private ItemStructurePart itemStructurePart;
    
    @OneToMany
    @JoinColumn(name = "base_item_item_structure_part_id")
    private List<ColorPossibility> colorPossibilities;

    
    public ItemStructurePart getItemStructurePart() {
        return itemStructurePart;
    }
    public void setItemStructurePart(ItemStructurePart itemStructurePart) {
        this.itemStructurePart = itemStructurePart;
    }
    public List<ColorPossibility> getColorPossibilities() {
        return colorPossibilities;
    }
    public void setColorPossibilities(List<ColorPossibility> colorPossibilities) {
        this.colorPossibilities = colorPossibilities;
    }

}
