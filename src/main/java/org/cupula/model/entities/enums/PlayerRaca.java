package org.cupula.model.entities.enums;

public enum PlayerRaca {
    ELFO(),
    HUMANO(),
    ANAO(),
    ORC(),
    GOBLIN(),
    GIGANTE();

    public static PlayerRaca fromId(Integer id) {
        if (id == null) return null;
        PlayerRaca[] values = PlayerRaca.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID inválido para PlayerRaca: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
}
