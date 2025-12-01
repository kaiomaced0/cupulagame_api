package org.cupula.repository;

import org.cupula.model.identificacao.Identificacao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdentificacaoRepository implements PanacheRepository<Identificacao> {   
    
}
