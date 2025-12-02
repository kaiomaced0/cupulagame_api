package org.cupula.repository.auth;

import org.cupula.model.auth.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {   
    
    public Usuario findByLogin(String login){
    if (login == null)
        return null;

    return find("login = ?1", login).firstResult();
}
}

