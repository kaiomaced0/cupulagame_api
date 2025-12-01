package org.cupula.repository;

import org.cupula.model.structures.Structure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StructureRepository implements PanacheRepository<Structure> {   
    
}
