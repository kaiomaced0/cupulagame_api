package org.cupula.model.entities.player;

import org.cupula.model.islands.Ilha;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class PlayerPosicao {
        // posicao atual do player
    private Long x;
    private Long y;
    private Long z;
    @ManyToOne
    private Ilha ilhaAtual;

    @OneToMany
    @JoinColumn(name = "player_id")
    private Player player;
    
    
}
