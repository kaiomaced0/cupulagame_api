package org.cupula.repository.banco;

import org.cupula.model.banco.TransacaoBancaria;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransacaoBancariaRepository implements PanacheRepository<TransacaoBancaria> {   
    
}

