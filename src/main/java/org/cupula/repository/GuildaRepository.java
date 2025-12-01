package org.cupula.repository;

import org.cupula.model.guilda.Guilda;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuildaRepository implements PanacheRepository<Guilda> {   
    
}
