package org.cupula.model.auth.enums;

public enum AuthProvider {
    LOCAL,
    GOOGLE,
    FACEBOOK,
    APPLE,
    MICROSOFT,
    GITHUB;

    public static AuthProvider fromId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de provider não pode ser nulo");
        }
        AuthProvider[] values = AuthProvider.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID de provider inválido: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
}
