package org.cupula.repository;

import org.cupula.model.items.baseview.BaseItemStructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaseItemStructureRepository implements PanacheRepository<BaseItemStructure> {   
    
}
