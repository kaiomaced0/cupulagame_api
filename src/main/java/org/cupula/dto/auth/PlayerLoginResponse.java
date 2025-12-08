package org.cupula.dto.auth;

public record PlayerLoginResponse(
        Long playerId,
        String token
) {
}
