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
