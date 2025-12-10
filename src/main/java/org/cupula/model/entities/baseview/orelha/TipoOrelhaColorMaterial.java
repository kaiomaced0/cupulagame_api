package org.cupula.model.entities.baseview.orelha;

import org.cupula.model.EntityClass;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;

@Entity
public class TipoOrelhaColorMaterial extends EntityClass {
    private ColorMaterial colorMaterial;
    private Long possibilidade;
    
    public ColorMaterial getColorMaterial() {
        return colorMaterial;
    }
    public void setColorMaterial(ColorMaterial colorMaterial) {
        this.colorMaterial = colorMaterial;
    }
    public Long getPossibilidade() {
        return possibilidade;
    }
    public void setPossibilidade(Long possibilidade) {
        this.possibilidade = possibilidade;
    }

}
