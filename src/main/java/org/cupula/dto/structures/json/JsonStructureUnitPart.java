package org.cupula.dto.structures.json;

public record JsonStructureUnitPart(
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
}
