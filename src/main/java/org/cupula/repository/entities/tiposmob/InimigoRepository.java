package org.cupula.repository.entities.tiposmob;

import org.cupula.model.entities.tiposmob.Inimigo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InimigoRepository implements PanacheRepository<Inimigo> {   
    
}

