package org.cupula.repository;

import java.util.List;
import java.util.Optional;

import org.cupula.model.pvp.PvpTurno;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PvpTurnoRepository implements PanacheRepository<PvpTurno> {
    
    public List<PvpTurno> findByPvp(Long pvpId) {
        return list("pvp.id", pvpId);
    }
    
    public Optional<PvpTurno> findByPvpAndNumero(Long pvpId, Long numero) {
        return find("pvp.id = ?1 and numero = ?2", pvpId, numero).firstResultOptional();
    }
    
    public List<PvpTurno> findByPvpOrderByNumero(Long pvpId) {
        return list("pvp.id = ?1 order by numero asc", pvpId);
    }
    
    public Optional<PvpTurno> findProximoTurno(Long pvpId) {
        return find("pvp.id = ?1 order by numero desc", pvpId).firstResultOptional();
    }
}
