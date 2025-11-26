package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.structures.view.ColorMaterial;
import org.cupula.model.structures.view.Texture;

public class Material {
    
    private String name;
    private List<Texture> textures;
    private Item item; 
    private Long quantidadeItemPorPixel;
    private List<ColorMaterial> colorsPossiveis;

    private Long resistenciaFogo;
    private Long resistenciaAgua;
    private Long resistenciaImpacto;
}
