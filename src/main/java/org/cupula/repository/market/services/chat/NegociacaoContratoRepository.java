package org.cupula.repository.market.services.chat;

import org.cupula.model.market.services.chat.NegociacaoContrato;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NegociacaoContratoRepository implements PanacheRepository<NegociacaoContrato> {   
    
}

