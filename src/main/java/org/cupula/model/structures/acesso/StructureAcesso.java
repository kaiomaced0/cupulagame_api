package org.cupula.model.structures.acesso;

import java.time.LocalDateTime;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "structure_acesso")
public class StructureAcesso extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    
    private Boolean acessoLiberado;
    private Boolean tempoRestrito;
    private LocalDateTime dataLimiteAcesso;

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Boolean getAcessoLiberado() {
        return acessoLiberado;
    }
    public void setAcessoLiberado(Boolean acessoLiberado) {
        this.acessoLiberado = acessoLiberado;
    }
    public Boolean getTempoRestrito() {
        return tempoRestrito;
    }
    public void setTempoRestrito(Boolean tempoRestrito) {
        this.tempoRestrito = tempoRestrito;
    }
    public LocalDateTime getDataLimiteAcesso() {
        return dataLimiteAcesso;
    }
    public void setDataLimiteAcesso(LocalDateTime dataLimiteAcesso) {
        this.dataLimiteAcesso = dataLimiteAcesso;
    }

}
