package org.cupula.repository.market.products;

import org.cupula.model.market.products.MarketItem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarketItemRepository implements PanacheRepository<MarketItem> {   
    
}

