package org.cupula.repository;

import org.cupula.model.structures.acesso.StructureAcesso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StructureAcessoRepository implements PanacheRepository<StructureAcesso> {   
    
}
