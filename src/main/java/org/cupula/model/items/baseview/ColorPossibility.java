package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ColorPossibility extends EntityClass {
    
    private List<ColorMaterial> colorsPossiveis;
    private Long possibilidade;
    public List<ColorMaterial> getColorsPossiveis() {
        return colorsPossiveis;
    }
    public void setColorsPossiveis(List<ColorMaterial> colorsPossiveis) {
        this.colorsPossiveis = colorsPossiveis;
    }
    public Long getPossibilidade() {
        return possibilidade;
    }
    public void setPossibilidade(Long possibilidade) {
        this.possibilidade = possibilidade;
    }

}
