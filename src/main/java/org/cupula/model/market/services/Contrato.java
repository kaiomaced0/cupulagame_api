package org.cupula.model.market.services;

import java.util.List;

import org.cupula.model.items.Item;
import org.cupula.model.market.services.chat.NegociacaoContrato;
import org.cupula.model.market.services.enums.ContratoStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;

@Entity
public class Contrato extends EntityClass {

    @ManyToOne
    @JoinColumn(name = "market_service_id")
    private MarketService marketService;

    @ManyToOne
    @JoinColumn(name = "negociacao_contrato_id")
    private NegociacaoContrato negociacao;
    
    @ManyToOne
    @JoinColumn(name = "contratante_id")
    private Player contratante;

    @Enumerated(EnumType.STRING)
    private ContratoStatus status;

    private Boolean valorPago;

    private Boolean itensEntregues;
    
    @ManyToMany
    @JoinTable(
        name = "contrato_itens_resultado"
        , joinColumns = @JoinColumn(name = "contrato_id")
        , inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itensResultado;
    
    
    public MarketService getMarketService() {
        return marketService;
    }
    public void setMarketService(MarketService marketService) {
        this.marketService = marketService;
    }
    public NegociacaoContrato getNegociacao() {
        return negociacao;
    }
    public void setNegociacao(NegociacaoContrato negociacao) {
        this.negociacao = negociacao;
    }
    public Player getContratante() {
        return contratante;
    }
    public void setContratante(Player contratante) {
        this.contratante = contratante;
    }
    public ContratoStatus getStatus() {
        return status;
    }
    public void setStatus(ContratoStatus status) {
        this.status = status;
    }
    public Boolean getValorPago() {
        return valorPago;
    }
    public void setValorPago(Boolean valorPago) {
        this.valorPago = valorPago;
    }
    public Boolean getItensEntregues() {
        return itensEntregues;
    }
    public void setItensEntregues(Boolean itensEntregues) {
        this.itensEntregues = itensEntregues;
    }
    public List<Item> getItensResultado() {
        return itensResultado;
    }
    public void setItensResultado(List<Item> itensResultado) {
        this.itensResultado = itensResultado;
    }

}
