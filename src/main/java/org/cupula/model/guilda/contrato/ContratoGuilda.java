package org.cupula.model.guilda.contrato;

import java.util.List;

import org.cupula.model.banco.TransacaoBancaria;
import org.cupula.model.guilda.enums.ContratoGuildaStatus;
import org.cupula.model.guilda.negociacao.NegociacaoServicoGuilda;

public class ContratoGuilda {
    
    private NegociacaoServicoGuilda negociacaoServicoGuilda;

    private List<ContratoGuildaParticipante> participantes;
    private ContratoGuildaStatus status;
    private List<TransacaoBancaria> transacoesBancariasRelacionadas;
    
}
