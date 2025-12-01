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
}
