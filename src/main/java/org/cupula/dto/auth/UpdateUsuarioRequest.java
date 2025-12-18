package org.cupula.dto.auth;

public record UpdateUsuarioRequest(
        String nickName,
        String email,
        Boolean loginLocalHabilitado,
        String senha) {
}
