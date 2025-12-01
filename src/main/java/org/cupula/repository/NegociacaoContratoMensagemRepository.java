package org.cupula.repository;

import org.cupula.model.market.services.chat.NegociacaoContratoMensagem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NegociacaoContratoMensagemRepository implements PanacheRepository<NegociacaoContratoMensagem> {   
    
}
