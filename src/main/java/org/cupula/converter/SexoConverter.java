package org.cupula.converter;

import org.cupula.model.auth.pessoa.Sexo;

import jakarta.ws.rs.ext.ParamConverter;

public class SexoConverter implements ParamConverter<Sexo> {

    @Override
    public Sexo fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return Sexo.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de sexo inválido: " + value);
        }
    }

    @Override
    public String toString(Sexo value) {
        return value != null ? value.getId().toString() : null;
    }
}
