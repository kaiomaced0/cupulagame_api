package org.cupula.repository.structures;

import org.cupula.model.structures.ItemStructurePart;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemStructurePartRepository implements PanacheRepository<ItemStructurePart> {   
    
}

