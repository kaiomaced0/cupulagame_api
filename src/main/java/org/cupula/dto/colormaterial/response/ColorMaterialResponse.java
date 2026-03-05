package org.cupula.dto.colormaterial.response;

import org.cupula.dto.texture.response.TextureResponse;
import org.cupula.model.structures.enums.ColorMaterialTipo;
import org.cupula.model.structures.view.ColorMaterial;

public record ColorMaterialResponse(
    Long id,
    String name,
    String hexCode,
    TextureResponse texture,
    ColorMaterialTipo tipo,
    Long createdAt,
    Long updatedAt
) {
    public static ColorMaterialResponse from(ColorMaterial colorMaterial) {
        if (colorMaterial == null) return null;
        
        return new ColorMaterialResponse(
            colorMaterial.getId(),
            colorMaterial.getName(),
            colorMaterial.getHexCode(),
            TextureResponse.from(colorMaterial.getTexture()),
            colorMaterial.getTipo(),
            colorMaterial.getDataInclusao() != null ? colorMaterial.getDataInclusao().toEpochSecond(java.time.ZoneOffset.UTC) : null,
            colorMaterial.getDataInclusao() != null ? colorMaterial.getDataInclusao().toEpochSecond(java.time.ZoneOffset.UTC) : null
        );
    }
}
