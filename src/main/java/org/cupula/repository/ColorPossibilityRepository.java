package org.cupula.repository;

import org.cupula.model.items.baseview.ColorPossibility;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ColorPossibilityRepository implements PanacheRepository<ColorPossibility> {   
    
}
