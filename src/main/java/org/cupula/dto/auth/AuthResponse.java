package org.cupula.dto.auth;

import java.util.Set;

import org.cupula.model.auth.enums.AuthProvider;
import org.cupula.model.auth.enums.Perfil;

public record AuthResponse(
        Long id,
        String login,
        String email,
        Boolean loginLocalHabilitado,
        Set<Perfil> perfis,
        Set<AuthProvider> providers,
        String token) {
}
