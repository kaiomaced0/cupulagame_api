package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.structures.ItemStructurePart;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class BaseItemItemStructurePart extends EntityClass {
    private ItemStructurePart itemStructurePart;
    private List<ColorPossibility> colorPossibilities;
}
