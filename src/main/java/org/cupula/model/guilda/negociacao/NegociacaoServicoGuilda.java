package org.cupula.model.guilda.negociacao;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.empresa.Empresa;
import org.cupula.model.entities.player.Player;
import org.cupula.model.guilda.CatalogoServicoGuilda;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.guilda.enums.NegociacaoServicoGuildaStatus;
import org.cupula.model.guilda.enums.NegociacaoTipoPagamento;
import org.cupula.model.islands.Ilha;
import org.cupula.model.items.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class NegociacaoServicoGuilda extends EntityClass {

    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;
    
    @ManyToOne
    @JoinColumn(name = "catalogo_servico_guilda_id")
    private CatalogoServicoGuilda catalogoServicoGuilda;

    private Long priceNegociado;

    @ManyToMany
    @JoinTable(
        name = "negociacao_servico_guilda_itens_oferecidos",
        joinColumns = @JoinColumn(name = "negociacao_servico_guilda_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itensOferecidos;

    @Enumerated(EnumType.STRING)
    private NegociacaoTipoPagamento tipoPagamento;

    private Long duracaoServicoEmMinutos;

    private Long tempoNegociadoParaRealizacaoEmMinutos;
    
    @Enumerated(EnumType.STRING)
    private NegociacaoServicoGuildaStatus status;

    @OneToMany
    @JoinColumn(name = "negociacao_servico_guilda_id")
    private List<NegociacaoServicoGuildaMensagem> mensagens;

    @ManyToOne
    @JoinColumn(name = "player_contratante_id")
    private Player playerContratante;

    @ManyToOne
    @JoinColumn(name = "guilda_contratante_id")
    private Guilda guildaContratante;

    @ManyToOne
    @JoinColumn(name = "empresa_contratante_id")
    private Empresa empresaContratante;

    @ManyToOne
    @JoinColumn(name = "ilha_contratante_id")
    private Ilha ilhaContratante;

    public Guilda getGuilda() {
        return guilda;
    }

    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }

    public CatalogoServicoGuilda getCatalogoServicoGuilda() {
        return catalogoServicoGuilda;
    }

    public void setCatalogoServicoGuilda(CatalogoServicoGuilda catalogoServicoGuilda) {
        this.catalogoServicoGuilda = catalogoServicoGuilda;
    }

    public Long getPriceNegociado() {
        return priceNegociado;
    }

    public void setPriceNegociado(Long priceNegociado) {
        this.priceNegociado = priceNegociado;
    }

    public List<Item> getItensOferecidos() {
        return itensOferecidos;
    }

    public void setItensOferecidos(List<Item> itensOferecidos) {
        this.itensOferecidos = itensOferecidos;
    }

    public NegociacaoTipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(NegociacaoTipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Long getDuracaoServicoEmMinutos() {
        return duracaoServicoEmMinutos;
    }

    public void setDuracaoServicoEmMinutos(Long duracaoServicoEmMinutos) {
        this.duracaoServicoEmMinutos = duracaoServicoEmMinutos;
    }

    public Long getTempoNegociadoParaRealizacaoEmMinutos() {
        return tempoNegociadoParaRealizacaoEmMinutos;
    }

    public void setTempoNegociadoParaRealizacaoEmMinutos(Long tempoNegociadoParaRealizacaoEmMinutos) {
        this.tempoNegociadoParaRealizacaoEmMinutos = tempoNegociadoParaRealizacaoEmMinutos;
    }

    public NegociacaoServicoGuildaStatus getStatus() {
        return status;
    }

    public void setStatus(NegociacaoServicoGuildaStatus status) {
        this.status = status;
    }

    public List<NegociacaoServicoGuildaMensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<NegociacaoServicoGuildaMensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public Player getPlayerContratante() {
        return playerContratante;
    }

    public void setPlayerContratante(Player playerContratante) {
        this.playerContratante = playerContratante;
    }

    public Guilda getGuildaContratante() {
        return guildaContratante;
    }

    public void setGuildaContratante(Guilda guildaContratante) {
        this.guildaContratante = guildaContratante;
    }

    public Empresa getEmpresaContratante() {
        return empresaContratante;
    }

    public void setEmpresaContratante(Empresa empresaContratante) {
        this.empresaContratante = empresaContratante;
    }

    public Ilha getIlhaContratante() {
        return ilhaContratante;
    }

    public void setIlhaContratante(Ilha ilhaContratante) {
        this.ilhaContratante = ilhaContratante;
    }



}
