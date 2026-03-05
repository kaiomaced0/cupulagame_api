package org.cupula.converter;

import org.cupula.model.entities.enums.MobTipo;

import jakarta.ws.rs.ext.ParamConverter;

public class MobTipoConverter implements ParamConverter<MobTipo> {

    @Override
    public MobTipo fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return MobTipo.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de tipo de mob inválido: " + value);
        }
    }

    @Override
    public String toString(MobTipo value) {
        return value != null ? value.getId().toString() : null;
    }
}
