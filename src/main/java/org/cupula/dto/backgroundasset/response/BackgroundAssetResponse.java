package org.cupula.dto.backgroundasset.response;

import org.cupula.model.configuracoes.BackgroundAsset;
import org.cupula.model.documento.Documento;

public record BackgroundAssetResponse(
    Long id,
    String nome,
    Long documentoId,
    String documentoNomeArquivo,
    String documentoCaminho
) {
    public static BackgroundAssetResponse from(BackgroundAsset backgroundAsset) {
        if (backgroundAsset == null) {
            return null;
        }

        Documento documento = backgroundAsset.getDocumento();

        return new BackgroundAssetResponse(
            backgroundAsset.getId(),
            backgroundAsset.getNome(),
            documento == null ? null : documento.getId(),
            documento == null ? null : documento.getNomeArquivo(),
            documento == null ? null : documento.getCaminho());
    }
}
