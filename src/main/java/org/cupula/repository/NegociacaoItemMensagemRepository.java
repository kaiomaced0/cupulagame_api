package org.cupula.repository;

import org.cupula.model.market.products.negociacao.NegociacaoItemMensagem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NegociacaoItemMensagemRepository implements PanacheRepository<NegociacaoItemMensagem> {   
    
}
