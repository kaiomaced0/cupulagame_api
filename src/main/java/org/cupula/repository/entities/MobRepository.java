package org.cupula.repository.entities;

import org.cupula.model.entities.mob.Mob;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MobRepository implements PanacheRepository<Mob> {   
    
}

