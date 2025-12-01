package org.cupula.repository;

import org.cupula.model.guilda.contrato.ContratoGuildaParticipante;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContratoGuildaParticipanteRepository implements PanacheRepository<ContratoGuildaParticipante> {   
    
}
