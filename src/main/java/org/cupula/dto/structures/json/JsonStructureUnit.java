package org.cupula.dto.structures.json;

import java.util.List;

import org.cupula.model.structures.enums.Layer;
import org.cupula.model.structures.enums.StructureUnitTipo;

public record JsonStructureUnit(
    String nome,
    Layer layer,
    StructureUnitTipo tipo,
    List<JsonStructureUnitPart> parts
) {
}
