package org.cupula.repository;

import org.cupula.model.structures.view.ColorMaterial;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ColorMaterialRepository implements PanacheRepository<ColorMaterial> {   
    
}
