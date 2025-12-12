package org.cupula.model.market.products.negociacao;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.products.enums.NegociacaoItemStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import org.cupula.model.EntityClass;

@Entity
public class NegociacaoItem extends EntityClass {
    
    @OneToMany
    @JoinColumn(name = "negociacao_item_id")
    private List<NegociacaoItemMensagem> mensagens;
    
    @ManyToMany
    @JoinTable(
        name = "negociacao_item_itens_troca",
        joinColumns = @JoinColumn(name = "negociacao_item_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
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
