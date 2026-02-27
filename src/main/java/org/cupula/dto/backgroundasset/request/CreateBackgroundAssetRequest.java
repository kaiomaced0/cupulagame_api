package org.cupula.dto.backgroundasset.request;

public record CreateBackgroundAssetRequest(
    String nome,
    Long documentoId
) {
}
