package org.cupula.dto.responses.usuario;

import java.util.List;
import java.util.Set;

import org.cupula.dto.responses.player.PlayerBasicDTO;
import org.cupula.model.auth.enums.AuthProvider;
import org.cupula.model.auth.enums.Perfil;

public record UsuarioLogadoDTO(
        Long id,
        String nickName,
        String email,
        Boolean loginLocalHabilitado,
        Set<Perfil> perfis,
        Set<AuthProvider> providers,
        List<PlayerBasicDTO> players) {
}
