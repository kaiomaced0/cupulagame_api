package org.cupula.repository;

import org.cupula.model.guilda.GuildaTag;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GuildaTagRepository implements PanacheRepository<GuildaTag> {   
    
}
