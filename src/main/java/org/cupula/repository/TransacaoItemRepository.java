package org.cupula.repository;

import org.cupula.model.banco.TransacaoItem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransacaoItemRepository implements PanacheRepository<TransacaoItem> {   
    
}
