package org.cupula.dto.structures.response;

import org.cupula.model.containers.Container;

public record ContainerResponse(
    Long id,
    StructureUnitResponse structureUnit
) {
    public static ContainerResponse from(Container container) {
        if (container == null) return null;
        
        return new ContainerResponse(
            container.getId(),
            StructureUnitResponse.from(container.getStructureUnit())
        );
    }
}
