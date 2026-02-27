package org.cupula.dto.backgroundasset.request;

public record UpdateBackgroundAssetRequest(
    String nome,
    Long documentoId
) {
}
