package org.cupula.repository.guilda;

import org.cupula.model.guilda.GuildaMembro;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuildaMembroRepository implements PanacheRepository<GuildaMembro> {   
    
}

