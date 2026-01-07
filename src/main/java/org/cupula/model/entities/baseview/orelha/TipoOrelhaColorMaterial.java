package org.cupula.model.entities.baseview.orelha;

import org.cupula.model.EntityClass;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "tipo_orelha_color_material")
public class TipoOrelhaColorMaterial extends EntityClass {
    @ManyToOne
    @JoinColumn(name = "color_material_id")
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
