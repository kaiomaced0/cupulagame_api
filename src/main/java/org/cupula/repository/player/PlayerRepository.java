package org.cupula.repository.player;

import java.util.List;

import org.cupula.model.auth.Usuario;
import org.cupula.model.entities.player.Player;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<Player> {

    /**
     * Busca todos os players de um usuário
     */
    public List<Player> findByUsuario(Usuario usuario) {
        return list("usuario ORDER BY id ASC", usuario);
    }

    /**
     * Conta quantos players um usuário possui
     */
    public long countByUsuario(Usuario usuario) {
        return count("usuario", usuario);
    }
}
