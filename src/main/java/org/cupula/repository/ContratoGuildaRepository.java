package org.cupula.repository;

import org.cupula.model.guilda.contrato.ContratoGuilda;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContratoGuildaRepository implements PanacheRepository<ContratoGuilda> {   
    
}
