package org.cupula.model.structures.view;

import org.cupula.model.structures.enums.ColorMaterialTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ColorMaterial extends EntityClass {
    private String name;
    private String hexCode;
    private Texture texture;
    private ColorMaterialTipo tipo;
}
