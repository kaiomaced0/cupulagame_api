package org.cupula.repository.pvp.aposta;

import java.util.List;

import org.cupula.model.pvp.aposta.PvpAposta;
import org.cupula.model.pvp.aposta.enums.PvpApostaStatus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PvpApostaRepository implements PanacheRepository<PvpAposta> {
    
    public List<PvpAposta> findByStatus(PvpApostaStatus status) {
        return list("status", status);
    }
    
    public List<PvpAposta> findByDesafiante(Long desafianteId) {
        return list("desafiante.id", desafianteId);
    }
    
    public List<PvpAposta> findByDesafiado(Long desafiadoId) {
        return list("desafiado.id", desafiadoId);
    }
    
    public List<PvpAposta> findByDesafianteOrDesafiado(Long playerId) {
        return list("desafiante.id = ?1 or desafiado.id = ?1", playerId);
    }
}

