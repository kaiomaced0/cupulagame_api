package org.cupula.dto.structures.response;

import java.util.List;
import java.util.stream.Collectors;

import org.cupula.model.structures.Structure;
import org.cupula.model.structures.enums.StructureTipo;

public record StructureResponse(
    Long id,
    String nome,
    StructureTipo tipo,
    List<StructureUnitResponse> structureUnits,
    List<ContainerResponse> containers
) {
    public static StructureResponse from(Structure structure) {
        if (structure == null) return null;
        
        List<StructureUnitResponse> unitsResponse = structure.getStructureUnits() != null
            ? structure.getStructureUnits().stream()
                .map(StructureUnitResponse::from)
                .collect(Collectors.toList())
            : List.of();
        
        List<ContainerResponse> containersResponse = structure.getContainers() != null
            ? structure.getContainers().stream()
                .map(ContainerResponse::from)
                .collect(Collectors.toList())
            : List.of();
        
        return new StructureResponse(
            structure.getId(),
            structure.getNome(),
            structure.getTipo(),
            unitsResponse,
            containersResponse
        );
    }
}
