package org.cupula.model.market.services.chat;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.QuantidadeItemList;
import org.cupula.model.market.enums.MarketTipoPagamento;
import org.cupula.model.market.services.MarketService;
import org.cupula.model.market.services.enums.NegociacaoContratoStatus;

import jakarta.persistence.Entity;

import org.cupula.model.EntityClass;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class NegociacaoContrato extends EntityClass {

    @ManyToOne
    @JoinColumn(name = "market_service_id")
    private MarketService marketService;

    @OneToMany
    @JoinColumn(name = "negociacao_contrato_id")
    private List<NegociacaoContratoMensagem> mensagens;

    @OneToMany
    @JoinColumn(name = "negociacao_contrato_id")
    private List<QuantidadeItemList> itensNecessarios;

    @OneToMany
    @JoinColumn(name = "negociacao_contrato_id")
    private List<QuantidadeItemList> itensQueSeraoEntregues;
    
    @ManyToMany
    @JoinTable(
        name = "negociacao_contrato_itens_troca"
        , joinColumns = @jakarta.persistence.JoinColumn(name = "negociacao_contrato_id")
        , inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "item_id")
    )
    private List<Item> intensTroca;
    private Long valorProposto;
    
    @Enumerated(EnumType.STRING)
    private MarketTipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    private NegociacaoContratoStatus status;

    
    public MarketService getMarketService() {
        return marketService;
    }
    public void setMarketService(MarketService marketService) {
        this.marketService = marketService;
    }
    public List<NegociacaoContratoMensagem> getMensagens() {
        return mensagens;
    }
    public void setMensagens(List<NegociacaoContratoMensagem> mensagens) {
        this.mensagens = mensagens;
    }
    public List<QuantidadeItemList> getItensNecessarios() {
        return itensNecessarios;
    }
    public void setItensNecessarios(List<QuantidadeItemList> itensNecessarios) {
        this.itensNecessarios = itensNecessarios;
    }
    public List<QuantidadeItemList> getItensQueSeraoEntregues() {
        return itensQueSeraoEntregues;
    }
    public void setItensQueSeraoEntregues(List<QuantidadeItemList> itensQueSeraoEntregues) {
        this.itensQueSeraoEntregues = itensQueSeraoEntregues;
    }
    public List<Item> getIntensTroca() {
        return intensTroca;
    }
    public void setIntensTroca(List<Item> intensTroca) {
        this.intensTroca = intensTroca;
    }
    public Long getValorProposto() {
        return valorProposto;
    }
    public void setValorProposto(Long valorProposto) {
        this.valorProposto = valorProposto;
    }
    public MarketTipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(MarketTipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    public NegociacaoContratoStatus getStatus() {
        return status;
    }
    public void setStatus(NegociacaoContratoStatus status) {
        this.status = status;
    }

}
