package org.cupula.model.comunity;

public enum VisibilidadePerfil {
    PUBLICO,    // Todos podem ver
    AMIGOS,     // Apenas amigos (nível Usuário) podem ver
    PRIVADO,    // Ninguém pode ver (exceto o próprio usuário)
    PERSONALIZADO; // Define manualmente quais amigos/players podem ver (usado na configuração específica)

    public static VisibilidadePerfil fromId(Integer id) {
        if (id == null) return null;
        VisibilidadePerfil[] values = VisibilidadePerfil.values();
        if (id < 0 || id >= values.length) {
            throw new IllegalArgumentException("ID inválido para VisibilidadePerfil: " + id);
        }
        return values[id];
    }

    public Integer getId() {
        return this.ordinal();
    }
}
