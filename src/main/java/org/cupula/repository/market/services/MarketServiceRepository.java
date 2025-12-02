package org.cupula.repository.market.services;

import org.cupula.model.market.services.MarketService;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarketServiceRepository implements PanacheRepository<MarketService> {   
    
}

