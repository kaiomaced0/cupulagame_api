package org.cupula.repository.islands.votacao;

import org.cupula.model.islands.votacao.IlhaVotacaoMembro;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaVotacaoMembroRepository implements PanacheRepository<IlhaVotacaoMembro> {   
    
}

