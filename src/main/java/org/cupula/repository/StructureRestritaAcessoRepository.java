package org.cupula.repository;

import org.cupula.model.structures.acesso.StructureRestritaAcesso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StructureRestritaAcessoRepository implements PanacheRepository<StructureRestritaAcesso> {   
    
}
