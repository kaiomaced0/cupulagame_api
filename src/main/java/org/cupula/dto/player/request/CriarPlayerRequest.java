package org.cupula.dto.player.request;

import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.view.ColorMaterial;

public record CriarPlayerRequest(
    PlayerRaca raca,
    Long tamanhoX,
    Long tamanhoY,
    Long tamanhoZ,
    Long tipoCabeloId,
    ColorMaterial corPele,
    Long tamanhoXOrelha,
    Long tamanhoYOrelha,
    Long tamanhoZOrelha,
    ColorMaterial corOrelha
) {
}
