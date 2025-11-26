package org.cupula.model.guilda;

import java.util.List;

import org.cupula.model.banco.TransacaoBancaria;
import org.cupula.model.guilda.enums.ContratoGuildaStatus;

public class ContratoGuilda {
    
    private NegociacaoServicoGuilda negociacaoServicoGuilda;

    private List<ContratoGuildaParticipante> participantes;
    private ContratoGuildaStatus status;
    private List<TransacaoBancaria> transacoesBancariasRelacionadas;
    
}
