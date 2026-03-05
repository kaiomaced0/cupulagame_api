package org.cupula.dto.colormaterial.request;

import org.cupula.model.structures.enums.ColorMaterialTipo;

public record CriarColorMaterialRequest(
    String name,
    String hexCode,
    Long textureId,
    ColorMaterialTipo tipo
) {
}
