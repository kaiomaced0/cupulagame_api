package org.cupula.dto.splashasset.response;

import org.cupula.model.configuracoes.SplashAsset;
import org.cupula.model.documento.Documento;

public record SplashAssetResponse(
    Long id,
    String nome,
    Long documentoId,
    String documentoNomeArquivo,
    String documentoCaminho
) {
    public static SplashAssetResponse from(SplashAsset splashAsset) {
        if (splashAsset == null) {
            return null;
        }

        Documento documento = splashAsset.getDocumento();

        return new SplashAssetResponse(
            splashAsset.getId(),
            splashAsset.getNome(),
            documento == null ? null : documento.getId(),
            documento == null ? null : documento.getNomeArquivo(),
            documento == null ? null : documento.getCaminho());
    }
}
