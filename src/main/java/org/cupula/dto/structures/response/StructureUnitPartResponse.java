package org.cupula.dto.structures.response;

import org.cupula.model.structures.StructureUnitPart;

public record StructureUnitPartResponse(
    Long id,
    String nome,
    Long inicioX,
    Long inicioY,
    Long inicioZ,
    Long fimX,
    Long fimY,
    Long fimZ,
    Long materialId,
    Long colorMaterialId,
    Boolean areaContato
) {
    public static StructureUnitPartResponse from(StructureUnitPart part) {
        if (part == null) return null;
        
        return new StructureUnitPartResponse(
            part.getId(),
            part.getNome(),
            part.getInicioX(),
            part.getInicioY(),
            part.getInicioZ(),
            part.getFimX(),
            part.getFimY(),
            part.getFimZ(),
            part.getMaterial() != null ? part.getMaterial().getId() : null,
            part.getColor() != null ? part.getColor().getId() : null,
            part.getAreaContato()
        );
    }
}
