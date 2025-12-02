package org.cupula.repository.items;

import org.cupula.model.items.ItemTipo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemTipoRepository implements PanacheRepository<ItemTipo> {   
    
}

