package org.cupula.repository;

import org.cupula.model.guilda.negociacao.NegociacaoServicoGuilda;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NegociacaoServicoGuildaRepository implements PanacheRepository<NegociacaoServicoGuilda> {   
    
}
