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
    public List<Item> getItens() {
        return itens;
    }
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public List<MarketItemList> getItensTroca() {
        return itensTroca;
    }
    public void setItensTroca(List<MarketItemList> itensTroca) {
        this.itensTroca = itensTroca;
    }
    public MarketTipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(MarketTipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    public MarketItemStatus getStatus() {
        return status;
    }
    public void setStatus(MarketItemStatus status) {
        this.status = status;
    }
    public Long getValorVendido() {
        return valorVendido;
    }
    public void setValorVendido(Long valorVendido) {
        this.valorVendido = valorVendido;
    }

}
