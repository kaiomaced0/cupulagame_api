package org.cupula.dto.player.response;

import org.cupula.model.comunity.VisibilidadePerfil;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.entities.player.Player;

public record PlayerResponse(
    Long id,
    Long usuarioId,
    VisibilidadePerfil visibilidade,
    PlayerRaca raca,
    Long tamanhoX,
    Long tamanhoY,
    Long tamanhoZ,
    Long tipoCabeloId,
    Long nivelAtual,
    Long xpAtual
) {
    public static PlayerResponse from(Player player) {
        return new PlayerResponse(
            player.getId(),
            player.getUsuario().getId(),
            player.getVisibilidade(),
            player.getRaca(),
            player.getTamanhoX(),
            player.getTamanhoY(),
            player.getTamanhoZ(),
            player.getTipoCabelo() != null ? player.getTipoCabelo().getId() : null,
            player.getStatus() != null ? player.getStatus().getNivelAtual() : 1L,
            player.getStatus() != null ? player.getStatus().getXpAtual() : 0L
        );
    }
}
