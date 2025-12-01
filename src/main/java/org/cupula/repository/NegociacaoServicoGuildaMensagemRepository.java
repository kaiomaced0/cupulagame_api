package org.cupula.repository;

import org.cupula.model.guilda.negociacao.NegociacaoServicoGuildaMensagem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NegociacaoServicoGuildaMensagemRepository implements PanacheRepository<NegociacaoServicoGuildaMensagem> {   
    
}
