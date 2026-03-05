package org.cupula.dto.colormaterial.request;

import org.cupula.model.structures.enums.ColorMaterialTipo;

public record AtualizarColorMaterialRequest(
    String name,
    String hexCode,
    Long textureId,
    ColorMaterialTipo tipo
) {
}
