package org.cupula.converter;

import org.cupula.model.structures.enums.ColorMaterialTipo;

import jakarta.ws.rs.ext.ParamConverter;

public class ColorMaterialTipoConverter implements ParamConverter<ColorMaterialTipo> {

    @Override
    public ColorMaterialTipo fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return ColorMaterialTipo.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de tipo de color material inválido: " + value);
        }
    }

    @Override
    public String toString(ColorMaterialTipo value) {
        return value != null ? value.getId().toString() : null;
    }
}
