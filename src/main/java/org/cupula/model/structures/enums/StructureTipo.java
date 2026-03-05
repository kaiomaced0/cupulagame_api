package org.cupula.model.structures.enums;

public enum StructureTipo {
    CONSTRUCAO(),
    CENARIO(),
    ILHA_ACESSO(),
    HOME_PLAYER(),
    LOJA_ILHA(),
    LOJA_EMPRESA(),
    EMPRESA(),
    GUILDA(),
    SALA_RESTRITA(),
    PRISAO(),
    GERAL();

    public static StructureTipo fromId(Integer id) {
        if (id == null) return null;
        StructureTipo[] values = StructureTipo.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID inválido para StructureTipo: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
    
}
