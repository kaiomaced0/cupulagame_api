package org.cupula.dto.usuario.response;

import java.time.LocalDateTime;

import org.cupula.model.comunity.StatusAmizade;

public record AmizadeResponse(
    Long id,
    Long solicitanteId,
    String solicitanteEmail,
    Long solicitadoId,
    String solicitadoEmail,
    StatusAmizade status,
    LocalDateTime dataSolicitacao,
    LocalDateTime dataResposta
) {
    public static AmizadeResponse from(org.cupula.model.comunity.Amizade amizade) {
        return new AmizadeResponse(
            amizade.getId(),
            amizade.getSolicitante().getId(),
            amizade.getSolicitante().getEmail(),
            amizade.getSolicitado().getId(),
            amizade.getSolicitado().getEmail(),
            amizade.getStatus(),
            amizade.getDataSolicitacao(),
            amizade.getDataResposta()
        );
    }
}
