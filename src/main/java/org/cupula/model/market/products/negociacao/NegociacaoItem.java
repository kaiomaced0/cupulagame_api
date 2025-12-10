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

    public List<NegociacaoItemMensagem> getMensagens() {
        return mensagens;
    }
    public void setMensagens(List<NegociacaoItemMensagem> mensagens) {
        this.mensagens = mensagens;
    }
    public List<Item> getItensTroca() {
        return itensTroca;
    }
    public void setItensTroca(List<Item> itensTroca) {
        this.itensTroca = itensTroca;
    }
    public Long getValorNegociado() {
        return valorNegociado;
    }
    public void setValorNegociado(Long valorNegociado) {
        this.valorNegociado = valorNegociado;
    }
    public NegociacaoItemStatus getStatus() {
        return status;
    }
    public void setStatus(NegociacaoItemStatus status) {
        this.status = status;
    }

}
