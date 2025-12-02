package org.cupula.repository.containers;

import org.cupula.model.containers.Inventario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventarioRepository implements PanacheRepository<Inventario> {   
    
}

