package org.cupula.repository.containers.enums;

import org.cupula.model.containers.enums.ContainerTipo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContainerTipoRepository implements PanacheRepository<ContainerTipo> {   
    
}

