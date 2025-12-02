package org.cupula.repository.guilda;

import org.cupula.model.guilda.CatalogoServicoGuilda;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CatalogoServicoGuildaRepository implements PanacheRepository<CatalogoServicoGuilda> {   
    
}

