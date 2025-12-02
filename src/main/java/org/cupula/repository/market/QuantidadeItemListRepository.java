package org.cupula.repository.market;

import org.cupula.model.market.QuantidadeItemList;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuantidadeItemListRepository implements PanacheRepository<QuantidadeItemList> {   
    
}

