package org.cupula.model.items.baseview;

import java.util.List;

import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

import org.cupula.model.EntityClass;

import jakarta.persistence.ManyToMany;

@Entity(name = "color_possibility")
public class ColorPossibility extends EntityClass {
    
    @ManyToMany
    @JoinTable(
        name = "color_possibility_color_material"
        , joinColumns = @jakarta.persistence.JoinColumn(name = "color_possibility_id")
        , inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "color_material_id")
    )
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
