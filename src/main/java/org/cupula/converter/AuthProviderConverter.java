package org.cupula.converter;

import org.cupula.model.auth.enums.AuthProvider;

import jakarta.ws.rs.ext.ParamConverter;

public class AuthProviderConverter implements ParamConverter<AuthProvider> {

    @Override
    public AuthProvider fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return AuthProvider.fromId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID de provider inválido: " + value);
        }
    }

    @Override
    public String toString(AuthProvider value) {
        return value != null ? value.getId().toString() : null;
    }
}
