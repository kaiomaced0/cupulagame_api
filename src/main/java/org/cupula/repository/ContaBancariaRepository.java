package org.cupula.repository;

import org.cupula.model.banco.ContaBancaria;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContaBancariaRepository implements PanacheRepository<ContaBancaria> {   
    
}
