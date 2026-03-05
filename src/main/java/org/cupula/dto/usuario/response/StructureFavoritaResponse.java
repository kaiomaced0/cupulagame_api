package org.cupula.dto.usuario.response;

import org.cupula.model.structures.Structure;
import org.cupula.model.structures.enums.StructureTipo;

public record StructureFavoritaResponse(
    Long id,
    String nome,
    StructureTipo tipo,
    String nomeTipo
) {
    public static StructureFavoritaResponse from(Structure structure) {
        if (structure == null) return null;
        
        return new StructureFavoritaResponse(
            structure.getId(),
            structure.getNome(),
            structure.getTipo(),
            structure.getTipo() != null ? structure.getTipo().name() : null
        );
    }
}
