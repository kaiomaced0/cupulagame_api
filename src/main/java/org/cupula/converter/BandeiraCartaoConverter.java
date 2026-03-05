package org.cupula.converter;

import org.cupula.model.auth.pagamento.BandeiraCartao;

import jakarta.ws.rs.ext.ParamConverter;

public class BandeiraCartaoConverter implements ParamConverter<BandeiraCartao> {

    @Override
    public BandeiraCartao fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return BandeiraCartao.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de bandeira de cartão inválido: " + value);
        }
    }

    @Override
    public String toString(BandeiraCartao value) {
        return value != null ? value.getId().toString() : null;
    }
}
