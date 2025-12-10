package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.structures.ItemStructurePart;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class BaseItemItemStructurePart extends EntityClass {
    private ItemStructurePart itemStructurePart;
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
