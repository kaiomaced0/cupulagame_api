package org.cupula.repository.entities;

import org.cupula.model.entities.Entity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntityRepository implements PanacheRepository<Entity> {   
    
}

