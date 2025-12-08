package org.cupula.repository.player;

import java.time.LocalDateTime;
import java.util.List;

import org.cupula.model.entities.player.Player;
import org.cupula.model.entities.player.PlayerMorte;
import org.cupula.model.entities.player.enums.TipoMorte;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerMorteRepository implements PanacheRepository<PlayerMorte> {

    /**
     * Busca todas as mortes de um player
     */
    public List<PlayerMorte> findByPlayer(Player player) {
        return list("player ORDER BY dataHoraMorte DESC", player);
    }

    /**
     * Busca mortes recentes (últimos X minutos)
     */
    public List<PlayerMorte> findRecentDeaths(Player player, int minutes) {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(minutes);
        return list("player = ?1 AND dataHoraMorte >= ?2 ORDER BY dataHoraMorte DESC", player, threshold);
    }

    /**
     * Conta mortes consecutivas recentes (para penalidade progressiva)
     */
    public long countMortesConsecutivas(Player player, int minutes) {
        return findRecentDeaths(player, minutes).size();
    }

    /**
     * Busca os últimos assassinos do player (para sistema de vingança)
     */
    public List<PlayerMorte> findTop10Assassinos(Player player) {
        return find("player = ?1 AND assassinoPlayer IS NOT NULL ORDER BY dataHoraMorte DESC", player)
                .page(0, 10)
                .list();
    }

    /**
     * Busca mortes não vindicadas (onde o player ainda não se vingou)
     */
    public List<PlayerMorte> findMortesNaoVindicadas(Player player) {
        return list("player = ?1 AND vindicado = false AND assassinoPlayer IS NOT NULL ORDER BY dataHoraMorte DESC", 
                    player);
    }

    /**
     * Busca mortes por tipo
     */
    public List<PlayerMorte> findByPlayerAndTipo(Player player, TipoMorte tipo) {
        return list("player = ?1 AND tipoMorte = ?2 ORDER BY dataHoraMorte DESC", player, tipo);
    }

    /**
     * Busca últimas N mortes de um player
     */
    public List<PlayerMorte> findLastN(Player player, int limit) {
        return find("player = ?1 ORDER BY dataHoraMorte DESC", player)
                .page(0, limit)
                .list();
    }

    /**
     * Remove mortes antigas (cleanup job)
     * Mantém apenas os últimos 50 registros ou últimos 30 dias
     */
    public long cleanupOldDeaths(Player player, int keepLast, int keepDays) {
        LocalDateTime threshold = LocalDateTime.now().minusDays(keepDays);
        
        // Busca mortes que podem ser deletadas
        List<PlayerMorte> allDeaths = findByPlayer(player);
        
        if (allDeaths.size() <= keepLast) {
            return 0; // Não precisa limpar
        }
        
        // Mantém as últimas 'keepLast' E as dos últimos 'keepDays' dias
        long deleted = 0;
        for (int i = keepLast; i < allDeaths.size(); i++) {
            PlayerMorte morte = allDeaths.get(i);
            if (morte.getDataHoraMorte().isBefore(threshold)) {
                delete(morte);
                deleted++;
            }
        }
        
        return deleted;
    }
}
