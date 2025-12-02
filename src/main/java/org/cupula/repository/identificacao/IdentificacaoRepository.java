package org.cupula.repository.identificacao;

import org.cupula.model.identificacao.Identificacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdentificacaoRepository implements PanacheRepository<Identificacao> {   
    
}

