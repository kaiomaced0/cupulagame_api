package org.cupula.model.identificacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class IdentificacaPlayer extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    
    @OneToOne
    @JoinColumn(name = "identificacao_id")
    private Identificacao identificacao;
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Identificacao getIdentificacao() {
        return identificacao;
    }
    public void setIdentificacao(Identificacao identificacao) {
        this.identificacao = identificacao;
    }

}
