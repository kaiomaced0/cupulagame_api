package org.cupula.model.market.services;

import java.util.List;

import org.cupula.model.entities.Player;
import org.cupula.model.items.Item;
import org.cupula.model.market.services.chat.NegociacaoContrato;
import org.cupula.model.market.services.enums.ContratoStatus;

public class Contrato {

    private MarketService marketService;
    private NegociacaoContrato negociacao;
    private Player contratante;

    private ContratoStatus status;

    private Boolean valorPago;

    private Boolean itensEntregues;
    private List<Item> itensResultado;
    
    
}
