package org.cupula.repository.entities;

import org.cupula.model.entities.player.Player;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<Player> {   
    
}

