package org.cupula.repository;

import org.cupula.model.items.tipositem.Consumivel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumivelRepository implements PanacheRepository<Consumivel> {   
    
}
