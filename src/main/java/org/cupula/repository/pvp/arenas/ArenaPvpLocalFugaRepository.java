package org.cupula.repository.pvp.arenas;

import java.util.List;

import org.cupula.model.pvp.arenas.ArenaPvp;
import org.cupula.model.pvp.arenas.ArenaPvpLocalFuga;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArenaPvpLocalFugaRepository implements PanacheRepository<ArenaPvpLocalFuga> {
    
    /**
     * Busca locais de fuga por arena
     */
    public List<ArenaPvpLocalFuga> findByArena(ArenaPvp arena) {
        return find("arena", arena).list();
    }
    
    /**
     * Busca locais de fuga por arena ID
     */
    public List<ArenaPvpLocalFuga> findByArenaId(Long arenaId) {
        return find("arena.id", arenaId).list();
    }
    
    /**
     * Busca local de fuga por coordenadas
     */
    public List<ArenaPvpLocalFuga> findByPosicao(Long x, Long y) {
        return find("x = ?1 and y = ?2", x, y).list();
    }
}

