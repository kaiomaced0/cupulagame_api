package org.cupula.model.structures;

import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ItemStructurePart extends EntityClass {
    
    private Long inicioX;
    private Long inicioY;
    private Long fimX;
    private Long fimY;
    private Long inicioZ;
    private Long fimZ;
    private Material material;
    private ColorMaterial color;
    private Boolean areaContato;
}
