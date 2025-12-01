package org.cupula.repository;

import org.cupula.model.islands.votacao.IlhaVotacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaVotacaoRepository implements PanacheRepository<IlhaVotacao> {   
    
}
