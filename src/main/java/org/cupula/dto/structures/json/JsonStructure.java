package org.cupula.dto.structures.json;

import java.util.List;

import org.cupula.model.structures.enums.StructureTipo;

public record JsonStructure(
    String nome,
    StructureTipo tipo,
    List<JsonStructureUnit> structureUnits,
    List<JsonContainer> containers
) {
}
