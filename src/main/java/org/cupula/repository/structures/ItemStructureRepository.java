package org.cupula.repository.structures;

import org.cupula.model.structures.ItemStructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemStructureRepository implements PanacheRepository<ItemStructure> {   
    
}

