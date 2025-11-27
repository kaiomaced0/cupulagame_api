package org.cupula.model.guilda.negociacao;

import java.util.List;

import org.cupula.model.empresa.Empresa;
import org.cupula.model.entities.Player;
import org.cupula.model.guilda.CatalogoServicoGuilda;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.guilda.enums.NegociacaoServicoGuildaStatus;
import org.cupula.model.guilda.enums.NegociacaoTipoPagamento;
import org.cupula.model.islands.Ilha;
import org.cupula.model.items.Item;

public class NegociacaoServicoGuilda {
    private Guilda guilda;
    private CatalogoServicoGuilda catalogoServicoGuilda;
    private Long priceNegociado;
    private List<Item> itensOferecidos;
    private NegociacaoTipoPagamento tipoPagamento;
    private Long duracaoServicoEmMinutos;
    private Long tempoNegociadoParaRealizacaoEmMinutos;
    private NegociacaoServicoGuildaStatus status;

    private List<NegociacaoServicoGuildaMensagem> mensagens;
    private Player playerContratante;
    private Guilda guildaContratante;
    private Empresa empresaContratante;
    private Ilha ilhaContratante;

}
