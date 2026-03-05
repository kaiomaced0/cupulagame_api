package org.cupula.model.entities.enums;

public enum MobTipo {
    INIMIGO,
    ANIMAL,
    VEICULO,
    MORADOR_LOCAL;

    public static MobTipo fromId(Integer id) {
        if (id == null) return null;
        MobTipo[] values = MobTipo.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID inválido para MobTipo: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
}
