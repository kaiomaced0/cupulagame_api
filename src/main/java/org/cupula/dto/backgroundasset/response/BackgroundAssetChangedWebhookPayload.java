package org.cupula.dto.backgroundasset.response;

public record BackgroundAssetChangedWebhookPayload(
    Long usuarioId,
    String usuarioNickName,
    Long backgroundAssetId,
    String backgroundAssetNome,
    Long documentoId,
    String documentoCaminho
) {
}
