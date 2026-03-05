package org.cupula.dto.usuario.response;

import org.cupula.model.structures.StructureUnit;
import org.cupula.model.structures.enums.StructureUnitTipo;

public record StructureUnitFavoritaResponse(
    Long id,
    String nome,
    StructureUnitTipo tipo,
    String nomeTipo
) {
    public static StructureUnitFavoritaResponse from(StructureUnit structureUnit) {
        if (structureUnit == null) return null;
        
        return new StructureUnitFavoritaResponse(
            structureUnit.getId(),
            structureUnit.getNome(),
            structureUnit.getTipo(),
            structureUnit.getTipo() != null ? structureUnit.getTipo().name() : null
        );
    }
}
