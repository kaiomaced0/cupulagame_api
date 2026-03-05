package org.cupula.converter;

import org.cupula.model.entities.enums.PlayerRaca;

import jakarta.ws.rs.ext.ParamConverter;

public class PlayerRacaConverter implements ParamConverter<PlayerRaca> {

    @Override
    public PlayerRaca fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return PlayerRaca.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de raça de player inválido: " + value);
        }
    }

    @Override
    public String toString(PlayerRaca value) {
        return value != null ? value.getId().toString() : null;
    }
}
