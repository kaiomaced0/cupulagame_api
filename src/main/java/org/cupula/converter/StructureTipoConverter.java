package org.cupula.converter;

import org.cupula.model.structures.enums.StructureTipo;

import jakarta.ws.rs.ext.ParamConverter;

public class StructureTipoConverter implements ParamConverter<StructureTipo> {

    @Override
    public StructureTipo fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return StructureTipo.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de tipo de estrutura inválido: " + value);
        }
    }

    @Override
    public String toString(StructureTipo value) {
        return value != null ? value.getId().toString() : null;
    }
}
