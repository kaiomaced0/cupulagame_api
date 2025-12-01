package org.cupula.model.market.services.chat;

import org.cupula.model.entities.Player;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class NegociacaoContratoMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    private Player contratante;
    private Player prestadorServico;
    
}
