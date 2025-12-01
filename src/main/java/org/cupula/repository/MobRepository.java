package org.cupula.repository;

import org.cupula.model.entities.Mob;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MobRepository implements PanacheRepository<Mob> {   
    
}
