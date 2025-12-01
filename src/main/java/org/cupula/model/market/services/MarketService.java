package org.cupula.model.market.services;

import java.util.List;

import org.cupula.model.entities.Player;
import org.cupula.model.islands.Ilha;
import org.cupula.model.market.QuantidadeItemList;
import org.cupula.model.market.enums.MarketTipoPagamento;
import org.cupula.model.market.services.enums.MarketServiceTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class MarketService extends EntityClass {

    private String titutlo;
    private String descricao;
    private Ilha ilha;
    private Player prestadorServico;
    private MarketServiceTipo marketServiceTipo;
    private List<MarketTipoPagamento> tiposPagamento;
    private List<QuantidadeItemList> itensNecessarios;
    private Long price;
    private List<QuantidadeItemList> itensTrocaPagamento;

    private List<QuantidadeItemList> itensPropaganda;
    
    private Long duracaoEmMinutos;

    private Long tempoLimiteParaAceitarContrato;

    private String proficienciaNoServico;

}
