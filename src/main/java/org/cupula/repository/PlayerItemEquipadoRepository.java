package org.cupula.repository;

import java.util.Optional;

import org.cupula.model.entities.PlayerItemEquipado;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerItemEquipadoRepository implements PanacheRepository<PlayerItemEquipado> {
    
    public Optional<PlayerItemEquipado> findByPlayer(Long playerId) {
        return find("player.id", playerId).firstResultOptional();
    }
    
    public Optional<PlayerItemEquipado> findByPlayerId(Long playerId) {
        return findByPlayer(playerId);
    }
}
