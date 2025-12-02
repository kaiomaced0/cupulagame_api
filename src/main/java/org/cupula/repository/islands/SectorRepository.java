package org.cupula.repository.islands;

import org.cupula.model.islands.Sector;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SectorRepository implements PanacheRepository<Sector> {   
    
}

