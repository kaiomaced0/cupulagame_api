package org.cupula.dto.auth;

import org.cupula.model.auth.enums.AuthProvider;

public record ProviderLoginRequest(
        AuthProvider provider,
        String externalId,
        String email,
        String displayName) {
}
