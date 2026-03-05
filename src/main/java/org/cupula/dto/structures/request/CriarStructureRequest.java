package org.cupula.dto.structures.request;

import org.cupula.model.structures.enums.StructureTipo;

public record CriarStructureRequest(
    String nome,
    StructureTipo tipo
) {
}
