package org.cupula.model.structures.enums;

import java.util.Arrays;

public enum ColorMaterialTipo {
    MATERIAL(1, "material"), 
    ORELHA(2, "orelha"), 
    CABELO(3, "cabelo"), 
    ROUPA(4, "roupa"), 
    PELE(5, "pele"), 
    ITEM(6, "item");

    private Integer id;
    private String tipo;

    ColorMaterialTipo(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public static ColorMaterialTipo fromId(Integer id) {
        if (id == null) {
            return null;
        }
        return Arrays.stream(ColorMaterialTipo.values())
                .filter(cmTipo -> cmTipo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ColorMaterialTipo inválido: " + id));
    }
}
