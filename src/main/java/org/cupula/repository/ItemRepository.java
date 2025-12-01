package org.cupula.repository;

import org.cupula.model.items.Item;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {   
    
}
