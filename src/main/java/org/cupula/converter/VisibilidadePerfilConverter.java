package org.cupula.converter;

import org.cupula.model.comunity.VisibilidadePerfil;

import jakarta.ws.rs.ext.ParamConverter;

public class VisibilidadePerfilConverter implements ParamConverter<VisibilidadePerfil> {

    @Override
    public VisibilidadePerfil fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return VisibilidadePerfil.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de visibilidade de perfil inválido: " + value);
        }
    }

    @Override
    public String toString(VisibilidadePerfil value) {
        return value != null ? value.getId().toString() : null;
    }
}
