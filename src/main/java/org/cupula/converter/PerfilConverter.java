package org.cupula.converter;

import org.cupula.model.auth.enums.Perfil;

import jakarta.ws.rs.ext.ParamConverter;

public class PerfilConverter implements ParamConverter<Perfil> {

    @Override
    public Perfil fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return Perfil.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de perfil inválido: " + value);
        }
    }

    @Override
    public String toString(Perfil value) {
        return value != null ? String.valueOf(value.getId()) : null;
    }
}
