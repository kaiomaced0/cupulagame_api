package org.cupula.repository.islands.regras;

import org.cupula.model.islands.regras.IlhaRegra;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaRegraRepository implements PanacheRepository<IlhaRegra> {   
    
}

