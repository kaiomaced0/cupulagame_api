package org.cupula.model.guilda.contrato;

import java.util.List;

import org.cupula.model.banco.TransacaoBancaria;
import org.cupula.model.guilda.enums.ContratoGuildaStatus;
import org.cupula.model.guilda.negociacao.NegociacaoServicoGuilda;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ContratoGuilda extends EntityClass {
    
    private NegociacaoServicoGuilda negociacaoServicoGuilda;

    private List<ContratoGuildaParticipante> participantes;
    private ContratoGuildaStatus status;
    private List<TransacaoBancaria> transacoesBancariasRelacionadas;
    
}
