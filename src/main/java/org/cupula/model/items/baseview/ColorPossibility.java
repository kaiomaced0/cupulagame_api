package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ColorPossibility extends EntityClass {
    
    private List<ColorMaterial> colorsPossiveis;
    private Long possibilidade;
}
