package org.cupula.model.identificacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;

@Entity
public class IdentificacaPlayer extends EntityClass {
    
    private Player player;
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
