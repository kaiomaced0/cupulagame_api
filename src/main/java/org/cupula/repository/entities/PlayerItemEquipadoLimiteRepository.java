package org.cupula.repository.entities;

import java.util.Optional;

import org.cupula.model.entities.PlayerItemEquipadoLimite;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerItemEquipadoLimiteRepository implements PanacheRepository<PlayerItemEquipadoLimite> {
    
    public Optional<PlayerItemEquipadoLimite> findByPlayer(Long playerId) {
        return find("player.id", playerId).firstResultOptional();
    }
    
    public Optional<PlayerItemEquipadoLimite> findByPlayerId(Long playerId) {
        return findByPlayer(playerId);
    }
}

