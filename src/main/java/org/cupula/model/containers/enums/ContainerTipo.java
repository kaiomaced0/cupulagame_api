package org.cupula.model.containers.enums;

public enum ContainerTipo {
    INVENTARIO,
    BAU,
    MOCHILA;

    public static ContainerTipo fromId(Integer id) {
        if (id == null) return null;
        ContainerTipo[] values = ContainerTipo.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID inválido para ContainerTipo: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
}
