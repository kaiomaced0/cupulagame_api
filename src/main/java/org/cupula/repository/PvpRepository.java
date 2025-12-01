package org.cupula.repository;

import org.cupula.model.pvp.Pvp;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PvpRepository implements PanacheRepository<Pvp> {   
    
}
