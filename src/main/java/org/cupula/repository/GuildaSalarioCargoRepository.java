package org.cupula.repository;

import org.cupula.model.guilda.GuildaSalarioCargo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuildaSalarioCargoRepository implements PanacheRepository<GuildaSalarioCargo> {   
    
}
