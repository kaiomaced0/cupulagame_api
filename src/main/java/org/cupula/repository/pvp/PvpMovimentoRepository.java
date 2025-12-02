package org.cupula.repository.pvp;

import java.util.List;

import org.cupula.model.pvp.PvpMovimento;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PvpMovimentoRepository implements PanacheRepository<PvpMovimento> {
    
    public List<PvpMovimento> findByPvp(Long pvpId) {
        return list("pvp.id", pvpId);
    }
    
    public List<PvpMovimento> findByTurno(Long turnoId) {
        return list("turno.id", turnoId);
    }
    
    public List<PvpMovimento> findByPlayer(Long playerId) {
        return list("player.id", playerId);
    }
    
    public List<PvpMovimento> findBySucesso(Boolean sucesso) {
        return list("sucesso", sucesso);
    }
    
    public List<PvpMovimento> findByPvpAndTurno(Long pvpId, Long turnoId) {
        return list("pvp.id = ?1 and turno.id = ?2", pvpId, turnoId);
    }
}

