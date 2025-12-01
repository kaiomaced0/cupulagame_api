package org.cupula.repository;

import org.cupula.model.islands.acessoilhas.IlhaPosicao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaPosicaoRepository implements PanacheRepository<IlhaPosicao> {   
    
}
