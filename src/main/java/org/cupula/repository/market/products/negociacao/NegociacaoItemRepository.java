package org.cupula.repository.market.products.negociacao;

import org.cupula.model.market.products.negociacao.NegociacaoItem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NegociacaoItemRepository implements PanacheRepository<NegociacaoItem> {   
    
}

