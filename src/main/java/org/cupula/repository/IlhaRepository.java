package org.cupula.repository;

import org.cupula.model.islands.Ilha;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaRepository implements PanacheRepository<Ilha> {   
    
}
