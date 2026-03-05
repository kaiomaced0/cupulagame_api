package org.cupula.dto.structures.response;

import java.util.List;
import java.util.stream.Collectors;

import org.cupula.model.structures.StructureUnit;
import org.cupula.model.structures.enums.Layer;
import org.cupula.model.structures.enums.StructureUnitTipo;

public record StructureUnitResponse(
    Long id,
    String nome,
    Layer layer,
    StructureUnitTipo tipo,
    List<StructureUnitPartResponse> parts
) {
    public static StructureUnitResponse from(StructureUnit unit) {
        if (unit == null) return null;
        
        List<StructureUnitPartResponse> partsResponse = unit.getParts() != null
            ? unit.getParts().stream()
                .map(StructureUnitPartResponse::from)
                .collect(Collectors.toList())
            : List.of();
        
        return new StructureUnitResponse(
            unit.getId(),
            unit.getNome(),
            unit.getLayer(),
            unit.getTipo(),
            partsResponse
        );
    }
}
