package org.cupula.dto.auth;

public record UpdateUsuarioRequest(
        String login,
        String email,
        Boolean loginLocalHabilitado,
        String senha) {
}
