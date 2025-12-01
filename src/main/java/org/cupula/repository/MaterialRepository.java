package org.cupula.repository;

import org.cupula.model.structures.Material;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaterialRepository implements PanacheRepository<Material> {   
    
}
