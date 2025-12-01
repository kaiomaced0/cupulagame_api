package org.cupula.repository;

import org.cupula.model.entities.tiposmob.Veiculo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VeiculoRepository implements PanacheRepository<Veiculo> {   
    
}
