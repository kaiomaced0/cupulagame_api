package org.cupula.model.market.services.chat;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;

@Entity
public class NegociacaoContratoMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    private Player contratante;
    private Player prestadorServico;
    
    public String getMensagemCriptografada() {
        return mensagemCriptografada;
    }
    public void setMensagemCriptografada(String mensagemCriptografada) {
        this.mensagemCriptografada = mensagemCriptografada;
    }
    public Player getContratante() {
        return contratante;
    }
    public void setContratante(Player contratante) {
        this.contratante = contratante;
    }
    public Player getPrestadorServico() {
        return prestadorServico;
    }
    public void setPrestadorServico(Player prestadorServico) {
        this.prestadorServico = prestadorServico;
    }

}
