package org.cupula.model.auth.pessoa;

public enum Sexo {
    MASCULINO,
    FEMININO,
    OUTRO;

    public static Sexo fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Sexo[] values = Sexo.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID de sexo inválido: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
}
