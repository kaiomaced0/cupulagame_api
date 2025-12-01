package org.cupula.repository;

import org.cupula.model.items.tipositem.Equipamento;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EquipamentoRepository implements PanacheRepository<Equipamento> {   
    
}
