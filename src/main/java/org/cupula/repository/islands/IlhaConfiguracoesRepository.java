package org.cupula.repository.islands;

import org.cupula.model.islands.IlhaConfiguracoes;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaConfiguracoesRepository implements PanacheRepository<IlhaConfiguracoes> {   
    
}

