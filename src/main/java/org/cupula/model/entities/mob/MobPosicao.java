package org.cupula.model.entities.mob;

import org.cupula.model.entities.player.Player;
import org.cupula.model.islands.Ilha;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class MobPosicao {
    
        // posicao atual do mob
    private Long x;
    private Long y;
    private Long z;

    @ManyToOne
    private Ilha ilhaAtual;

    @OneToMany
    @JoinColumn(name = "mob_id")
    private Mob mob;
    
}
