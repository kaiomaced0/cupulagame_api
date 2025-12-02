package org.cupula.repository.islands.cargos;

import org.cupula.model.islands.cargos.IlhaMembro;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaMembroRepository implements PanacheRepository<IlhaMembro> {   
    
}

