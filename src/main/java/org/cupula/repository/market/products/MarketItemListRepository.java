package org.cupula.repository.market.products;

import org.cupula.model.market.products.MarketItemList;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarketItemListRepository implements PanacheRepository<MarketItemList> {   
    
}

