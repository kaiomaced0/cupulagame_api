package org.cupula.model.auth;

import java.util.Objects;

import org.cupula.model.auth.enums.AuthProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class UsuarioProvider {

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, length = 30)
    private AuthProvider provider;

    @Column(name = "provider_uid", nullable = false, length = 120)
    private String externalId;

    @Column(name = "provider_email", length = 120)
    private String email;

    @Column(name = "provider_nome", length = 120)
    private String displayName;

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioProvider that)) return false;
        return provider == that.provider && Objects.equals(externalId, that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provider, externalId);
    }
}
