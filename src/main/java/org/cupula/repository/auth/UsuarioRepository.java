package org.cupula.repository.auth;

import org.cupula.model.auth.Usuario;
import org.cupula.model.auth.UsuarioProvider;
import org.cupula.model.auth.enums.AuthProvider;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {   
    
    public Usuario findByNickName(String nickName) {
        if (nickName == null) {
            return null;
        }
        return find("nickname = ?1", nickName).firstResult();
    }

    public Usuario findByProvider(AuthProvider provider, String externalId) {
        if (provider == null || externalId == null) {
            return null;
        }
        return find("provedoresLogin.provider = ?1 and provedoresLogin.externalId = ?2", provider, externalId)
                .firstResult();
    }

    public Usuario findByEmail(String email) {
        if (email == null) {
            return null;
        }
        return find("email = ?1", email).firstResult();
    }

    public boolean hasProvider(Usuario usuario, AuthProvider provider) {
        if (usuario == null || provider == null || usuario.getProvedoresLogin() == null) {
            return false;
        }
        return usuario.getProvedoresLogin().stream()
                .map(UsuarioProvider::getProvider)
                .anyMatch(provider::equals);
    }
}

