package org.cupula.model.market.products;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.enums.MarketTipoPagamento;
import org.cupula.model.market.products.enums.MarketItemStatus;

public class MarketItem {
    
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
