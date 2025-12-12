package org.cupula.model.market.services.chat;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NegociacaoContratoMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    @ManyToOne
    @JoinColumn(name = "contratante_id")
    private Player contratante;

    @ManyToOne
    @JoinColumn(name = "prestador_servico_id")
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
