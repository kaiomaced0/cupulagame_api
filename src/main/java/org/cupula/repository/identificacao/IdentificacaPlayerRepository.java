package org.cupula.repository.identificacao;

import org.cupula.model.identificacao.IdentificacaPlayer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdentificacaPlayerRepository implements PanacheRepository<IdentificacaPlayer> {   
    
}

