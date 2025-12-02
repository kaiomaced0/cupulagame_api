package org.cupula.model.structures.enums;

public enum ColorMaterialTipo {
    MATERIAL("material"), ORELHA("orelha"), CABELO("cabelo"), ROUPA("roupa"), PELE("pele"), ITEM("item");

    private String tipo;

    ColorMaterialTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
