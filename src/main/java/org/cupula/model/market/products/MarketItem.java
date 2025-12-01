package org.cupula.model.market.products;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.enums.MarketTipoPagamento;
import org.cupula.model.market.products.enums.MarketItemStatus;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class MarketItem extends EntityClass {
    
    private List<Item> itens;
    private Long price;
    private int quantity;
    private String descricao;
    private String titulo;
    private List<MarketItemList> itensTroca;
    private MarketTipoPagamento tipoPagamento;
    private MarketItemStatus status;
    private Long valorVendido;
}
