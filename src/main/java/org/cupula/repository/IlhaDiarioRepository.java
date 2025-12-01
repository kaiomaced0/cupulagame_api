package org.cupula.repository;

import org.cupula.model.islands.regras.IlhaDiario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaDiarioRepository implements PanacheRepository<IlhaDiario> {   
    
}
