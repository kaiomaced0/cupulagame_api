package org.cupula.repository.items.baseview;

import org.cupula.model.items.baseview.BaseItemStructureUnitPart;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaseItemStructureUnitPartRepository implements PanacheRepository<BaseItemStructureUnitPart> {
    
}
