package org.cupula.model.guilda.negociacao;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.empresa.Empresa;
import org.cupula.model.entities.Player;
import org.cupula.model.guilda.CatalogoServicoGuilda;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.guilda.enums.NegociacaoServicoGuildaStatus;
import org.cupula.model.guilda.enums.NegociacaoTipoPagamento;
import org.cupula.model.islands.Ilha;
import org.cupula.model.items.Item;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class NegociacaoServicoGuilda extends EntityClass {
    @ManyToOne
    private Guilda guilda;
    @ManyToOne
    private CatalogoServicoGuilda catalogoServicoGuilda;
    private Long priceNegociado;
    @ManyToMany
    private List<Item> itensOferecidos;
    @Enumerated(EnumType.STRING)
    private NegociacaoTipoPagamento tipoPagamento;
    private Long duracaoServicoEmMinutos;
    private Long tempoNegociadoParaRealizacaoEmMinutos;
    @Enumerated(EnumType.STRING)
    private NegociacaoServicoGuildaStatus status;

    @OneToMany
    private List<NegociacaoServicoGuildaMensagem> mensagens;
    @ManyToOne
    private Player playerContratante;
    @ManyToOne
    private Guilda guildaContratante;
    @ManyToOne
    private Empresa empresaContratante;
    @ManyToOne
    private Ilha ilhaContratante;

}
