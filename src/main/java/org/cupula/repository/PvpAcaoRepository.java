package org.cupula.repository;

import org.cupula.model.pvp.PvpAcao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PvpAcaoRepository implements PanacheRepository<PvpAcao> {   
    
}
