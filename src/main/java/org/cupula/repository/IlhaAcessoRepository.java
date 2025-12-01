package org.cupula.repository;

import org.cupula.model.islands.acessoilhas.IlhaAcesso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaAcessoRepository implements PanacheRepository<IlhaAcesso> {   
    
}
