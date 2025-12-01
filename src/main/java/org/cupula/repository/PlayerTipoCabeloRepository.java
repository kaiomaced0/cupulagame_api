package org.cupula.repository;

import org.cupula.model.entities.baseview.PlayerTipoCabelo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerTipoCabeloRepository implements PanacheRepository<PlayerTipoCabelo> {   
    
}
