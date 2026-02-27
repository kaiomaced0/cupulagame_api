package org.cupula.dto.backgroundasset.request;

public record AssignBackgroundAssetRequest(
    Long usuarioId,
    Long backgroundAssetId
) {
}
