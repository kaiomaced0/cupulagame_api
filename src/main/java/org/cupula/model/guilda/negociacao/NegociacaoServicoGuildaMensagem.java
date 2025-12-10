package org.cupula.model.guilda.negociacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;
import org.cupula.model.guilda.Guilda;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NegociacaoServicoGuildaMensagem extends EntityClass {
    private String mensagemCriptografada;
    private Player remetente;

    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;

    public String getMensagemCriptografada() {
        return mensagemCriptografada;
    }

    public void setMensagemCriptografada(String mensagemCriptografada) {
        this.mensagemCriptografada = mensagemCriptografada;
    }

    public Player getRemetente() {
        return remetente;
    }

    public void setRemetente(Player remetente) {
        this.remetente = remetente;
    }

    public Guilda getGuilda() {
        return guilda;
    }

    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }
    
    
}
