package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.structures.view.ColorMaterial;
import org.cupula.model.structures.view.Texture;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;

import org.cupula.model.EntityClass;
import org.cupula.model.items.ItemTipo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;

@Entity
public class Material extends EntityClass {
    
    private String name;
    @ManyToMany
    @JoinTable(
        name = "material_textures"
        , joinColumns = @jakarta.persistence.JoinColumn(name = "material_id")
        , inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "texture_id")
    )
    private List<Texture> textures;

    @Enumerated(EnumType.STRING)
    private ItemTipo itemTipo; 

    private Long quantidadeItemPorPixel;
    @ManyToMany
    @JoinTable(
        name = "material_color_materials"
        , joinColumns = @jakarta.persistence.JoinColumn(name = "material_id")
        , inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "color_material_id")
    )
    private List<ColorMaterial> colorsPossiveis;

    private Long resistenciaFogo;
    private Long resistenciaAgua;
    private Long resistenciaImpacto;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Texture> getTextures() {
        return textures;
    }
    public void setTextures(List<Texture> textures) {
        this.textures = textures;
    }
    public Long getQuantidadeItemPorPixel() {
        return quantidadeItemPorPixel;
    }
    public void setQuantidadeItemPorPixel(Long quantidadeItemPorPixel) {
        this.quantidadeItemPorPixel = quantidadeItemPorPixel;
    }
    public List<ColorMaterial> getColorsPossiveis() {
        return colorsPossiveis;
    }
    public void setColorsPossiveis(List<ColorMaterial> colorsPossiveis) {
        this.colorsPossiveis = colorsPossiveis;
    }
    public Long getResistenciaFogo() {
        return resistenciaFogo;
    }
    public void setResistenciaFogo(Long resistenciaFogo) {
        this.resistenciaFogo = resistenciaFogo;
    }
    public Long getResistenciaAgua() {
        return resistenciaAgua;
    }
    public void setResistenciaAgua(Long resistenciaAgua) {
        this.resistenciaAgua = resistenciaAgua;
    }
    public Long getResistenciaImpacto() {
        return resistenciaImpacto;
    }
    public void setResistenciaImpacto(Long resistenciaImpacto) {
        this.resistenciaImpacto = resistenciaImpacto;
    }
    public ItemTipo getItemTipo() {
        return itemTipo;
    }
    public void setItemTipo(ItemTipo itemTipo) {
        this.itemTipo = itemTipo;
    }

}
