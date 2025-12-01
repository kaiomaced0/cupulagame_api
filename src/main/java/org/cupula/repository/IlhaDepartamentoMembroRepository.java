package org.cupula.repository;

import org.cupula.model.islands.cargos.IlhaDepartamentoMembro;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaDepartamentoMembroRepository implements PanacheRepository<IlhaDepartamentoMembro> {   
    
}
