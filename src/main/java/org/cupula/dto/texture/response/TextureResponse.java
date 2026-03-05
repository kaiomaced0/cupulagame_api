package org.cupula.dto.texture.response;

import org.cupula.model.structures.view.Texture;

public record TextureResponse(
    Long id,
    String name,
    String path,
    Long createdAt,
    Long updatedAt
) {
    public static TextureResponse from(Texture texture) {
        if (texture == null) return null;
        
        return new TextureResponse(
            texture.getId(),
            texture.getName(),
            texture.getPath(),
            texture.getDataInclusao() != null ? texture.getDataInclusao().toEpochSecond(java.time.ZoneOffset.UTC) : null,
            texture.getDataInclusao() != null ? texture.getDataInclusao().toEpochSecond(java.time.ZoneOffset.UTC) : null
        );
    }
}
