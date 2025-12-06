package org.cupula.model.market.services;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.services.chat.NegociacaoContrato;
import org.cupula.model.market.services.enums.ContratoStatus;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

@Entity
public class Contrato extends EntityClass {

    private MarketService marketService;
    private NegociacaoContrato negociacao;
    private Player contratante;

    private ContratoStatus status;

    private Boolean valorPago;

    private Boolean itensEntregues;
    private List<Item> itensResultado;
    
    
}
