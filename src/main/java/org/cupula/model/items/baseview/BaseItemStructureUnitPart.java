package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.structures.StructureUnitPart;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "base_item_structure_unit_part")
public class BaseItemStructureUnitPart extends EntityClass {

    @OneToOne
    @JoinColumn(name = "structure_unit_part_id")
    private StructureUnitPart structureUnitPart;
    
    @OneToMany
    @JoinColumn(name = "base_item_structure_unit_part_id")
    private List<ColorPossibility> colorPossibilities;

    
    public StructureUnitPart getStructureUnitPart() {
        return structureUnitPart;
    }
    public void setStructureUnitPart(StructureUnitPart structureUnitPart) {
        this.structureUnitPart = structureUnitPart;
    }
    public List<ColorPossibility> getColorPossibilities() {
        return colorPossibilities;
    }
    public void setColorPossibilities(List<ColorPossibility> colorPossibilities) {
        this.colorPossibilities = colorPossibilities;
    }

}
