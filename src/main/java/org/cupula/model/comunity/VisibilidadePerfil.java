package org.cupula.model.comunity;

public enum VisibilidadePerfil {
    PUBLICO,    // Todos podem ver
    AMIGOS,     // Apenas amigos (nível Usuário) podem ver
    PRIVADO,    // Ninguém pode ver (exceto o próprio usuário)
    PERSONALIZADO // Define manualmente quais amigos/players podem ver (usado na configuração específica)
}
