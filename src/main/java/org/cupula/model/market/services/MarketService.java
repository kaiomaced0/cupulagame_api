package org.cupula.model.market.services;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.Player;
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

}
