package org.cupula.dto.auth;

public record CreateUsuarioRequest(String login, String email, String senha) {
}
