package org.cupula.dto.usuario.response;

import java.util.List;

public record FavoritasResponse(
    List<StructureFavoritaResponse> structures,
    List<StructureUnitFavoritaResponse> structureUnits
) {
}
