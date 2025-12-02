package org.cupula.repository.structures.view;

import org.cupula.model.structures.view.Texture;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextureRepository implements PanacheRepository<Texture> {   
    
}

