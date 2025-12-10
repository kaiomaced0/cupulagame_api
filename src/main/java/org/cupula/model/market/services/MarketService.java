package org.cupula.model.market.services;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;
import org.cupula.model.islands.Ilha;
import org.cupula.model.market.QuantidadeItemList;
import org.cupula.model.market.enums.MarketTipoPagamento;
import org.cupula.model.market.services.enums.MarketServiceTipo;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class MarketService extends EntityClass {

    private String titutlo;
    private String descricao;
    @ManyToOne
    private Ilha ilha;
    @ManyToOne
    private Player prestadorServico;
    @Enumerated(EnumType.STRING)
    private MarketServiceTipo marketServiceTipo;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MarketTipoPagamento> tiposPagamento;
    private List<QuantidadeItemList> itensNecessarios;
    private Long price;
    private List<QuantidadeItemList> itensTrocaPagamento;

    private List<QuantidadeItemList> itensPropaganda;
    
    private Long duracaoEmMinutos;

    private Long tempoLimiteParaAceitarContrato;

    private String proficienciaNoServico;

    public String getTitutlo() {
        return titutlo;
    }
    public void setTitutlo(String titutlo) {
        this.titutlo = titutlo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }
    public Player getPrestadorServico() {
        return prestadorServico;
    }
    public void setPrestadorServico(Player prestadorServico) {
        this.prestadorServico = prestadorServico;
    }
    public MarketServiceTipo getMarketServiceTipo() {
        return marketServiceTipo;
    }
    public void setMarketServiceTipo(MarketServiceTipo marketServiceTipo) {
        this.marketServiceTipo = marketServiceTipo;
    }
    public List<MarketTipoPagamento> getTiposPagamento() {
        return tiposPagamento;
    }
    public void setTiposPagamento(List<MarketTipoPagamento> tiposPagamento) {
        this.tiposPagamento = tiposPagamento;
    }
    public List<QuantidadeItemList> getItensNecessarios() {
        return itensNecessarios;
    }
    public void setItensNecessarios(List<QuantidadeItemList> itensNecessarios) {
        this.itensNecessarios = itensNecessarios;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public List<QuantidadeItemList> getItensTrocaPagamento() {
        return itensTrocaPagamento;
    }
    public void setItensTrocaPagamento(List<QuantidadeItemList> itensTrocaPagamento) {
        this.itensTrocaPagamento = itensTrocaPagamento;
    }
    public List<QuantidadeItemList> getItensPropaganda() {
        return itensPropaganda;
    }
    public void setItensPropaganda(List<QuantidadeItemList> itensPropaganda) {
        this.itensPropaganda = itensPropaganda;
    }
    public Long getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }
    public void setDuracaoEmMinutos(Long duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }
    public Long getTempoLimiteParaAceitarContrato() {
        return tempoLimiteParaAceitarContrato;
    }
    public void setTempoLimiteParaAceitarContrato(Long tempoLimiteParaAceitarContrato) {
        this.tempoLimiteParaAceitarContrato = tempoLimiteParaAceitarContrato;
    }
    public String getProficienciaNoServico() {
        return proficienciaNoServico;
    }
    public void setProficienciaNoServico(String proficienciaNoServico) {
        this.proficienciaNoServico = proficienciaNoServico;
    }

}
