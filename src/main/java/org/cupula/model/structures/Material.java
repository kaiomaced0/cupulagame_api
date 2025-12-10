package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.structures.view.ColorMaterial;
import org.cupula.model.structures.view.Texture;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Material extends EntityClass {
    
    private String name;
    private List<Texture> textures;
    private Item item; 
    private Long quantidadeItemPorPixel;
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
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
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

}
