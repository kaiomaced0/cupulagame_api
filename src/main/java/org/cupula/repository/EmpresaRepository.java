package org.cupula.repository;

import org.cupula.model.empresa.Empresa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmpresaRepository implements PanacheRepository<Empresa> {   
    
}
