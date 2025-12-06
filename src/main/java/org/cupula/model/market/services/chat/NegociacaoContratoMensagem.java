package org.cupula.model.market.services.chat;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;

@Entity
public class NegociacaoContratoMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    private Player contratante;
    private Player prestadorServico;
    
}
