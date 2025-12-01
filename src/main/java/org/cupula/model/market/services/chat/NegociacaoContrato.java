package org.cupula.model.market.services.chat;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.QuantidadeItemList;
import org.cupula.model.market.enums.MarketTipoPagamento;
import org.cupula.model.market.services.MarketService;
import org.cupula.model.market.services.enums.NegociacaoContratoStatus;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class NegociacaoContrato extends EntityClass {
    private MarketService marketService;
    private List<NegociacaoContratoMensagem> mensagens;
    private List<QuantidadeItemList> itensNecessarios;
    private List<QuantidadeItemList> itensQueSeraoEntregues;
    private List<Item> intensTroca;
    private Long valorProposto;
    private MarketTipoPagamento tipoPagamento;
    private NegociacaoContratoStatus status;
}
