package org.cupula.dto.responses.usuario;

import java.util.Set;

import org.cupula.model.auth.enums.AuthProvider;
import org.cupula.model.auth.enums.Perfil;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String email,
        Boolean loginLocalHabilitado,
        Set<Perfil> perfis,
        Set<AuthProvider> providers) {
}
