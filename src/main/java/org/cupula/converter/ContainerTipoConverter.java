package org.cupula.converter;

import org.cupula.model.containers.enums.ContainerTipo;

import jakarta.ws.rs.ext.ParamConverter;

public class ContainerTipoConverter implements ParamConverter<ContainerTipo> {

    @Override
    public ContainerTipo fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return ContainerTipo.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de tipo de container inválido: " + value);
        }
    }

    @Override
    public String toString(ContainerTipo value) {
        return value != null ? value.getId().toString() : null;
    }
}
