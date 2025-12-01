package org.cupula.model.market.products.negociacao;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.products.enums.NegociacaoItemStatus;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class NegociacaoItem extends EntityClass {
    
    private List<NegociacaoItemMensagem> mensagens;
    private List<Item> itensTroca;
    private Long valorNegociado;
    private NegociacaoItemStatus status;

}
