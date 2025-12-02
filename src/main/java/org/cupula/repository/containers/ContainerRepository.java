package org.cupula.repository.containers;

import org.cupula.model.containers.Container;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContainerRepository implements PanacheRepository<Container> {   
    
}

