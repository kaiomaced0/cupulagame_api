package org.cupula.repository.player;

import java.util.List;
import java.util.Optional;

import org.cupula.model.player.PlayerSlotPadrao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerSlotPadraoRepository implements PanacheRepository<PlayerSlotPadrao> {
    
    public List<PlayerSlotPadrao> findByPlayer(Long playerId) {
        return list("player.id", playerId);
    }
    
    public Optional<PlayerSlotPadrao> findByPlayerAndOpcao(Long playerId, Integer opcao) {
        return find("player.id = ?1 and opcao = ?2", playerId, opcao).firstResultOptional();
    }
    
    public List<PlayerSlotPadrao> findByPlayerOrderByOpcao(Long playerId) {
        return list("player.id = ?1 order by opcao asc", playerId);
    }
}

