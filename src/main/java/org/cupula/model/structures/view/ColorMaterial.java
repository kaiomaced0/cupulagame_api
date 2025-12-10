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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHexCode() {
        return hexCode;
    }
    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }
    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public ColorMaterialTipo getTipo() {
        return tipo;
    }
    public void setTipo(ColorMaterialTipo tipo) {
        this.tipo = tipo;
    }

}
