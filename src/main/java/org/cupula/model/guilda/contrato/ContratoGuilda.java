package org.cupula.model.guilda.contrato;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.banco.TransacaoBancaria;
import org.cupula.model.guilda.enums.ContratoGuildaStatus;
import org.cupula.model.guilda.negociacao.NegociacaoServicoGuilda;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "contrato_guilda")
public class ContratoGuilda extends EntityClass {
    
    @OneToOne
    @JoinColumn(name = "contrato_guilda_id")
    private NegociacaoServicoGuilda negociacaoServicoGuilda;

    @OneToMany
    @JoinColumn(name = "contrato_guilda_id")
    private List<ContratoGuildaParticipante> participantes;

    @Enumerated(EnumType.STRING)
    private ContratoGuildaStatus status;

    @OneToMany
    @JoinTable(
        name = "contrato_guilda_transacao_bancaria",
        joinColumns = @JoinColumn(name = "contrato_guilda_id"),
        inverseJoinColumns = @JoinColumn(name = "transacao_bancaria_id")
    )
    private List<TransacaoBancaria> transacoesBancariasRelacionadas;
    
    public NegociacaoServicoGuilda getNegociacaoServicoGuilda() {
        return negociacaoServicoGuilda;
    }
    public void setNegociacaoServicoGuilda(NegociacaoServicoGuilda negociacaoServicoGuilda) {
        this.negociacaoServicoGuilda = negociacaoServicoGuilda;
    }
    public List<ContratoGuildaParticipante> getParticipantes() {
        return participantes;
    }
    public void setParticipantes(List<ContratoGuildaParticipante> participantes) {
        this.participantes = participantes;
    }
    public ContratoGuildaStatus getStatus() {
        return status;
    }
    public void setStatus(ContratoGuildaStatus status) {
        this.status = status;
    }
    public List<TransacaoBancaria> getTransacoesBancariasRelacionadas() {
        return transacoesBancariasRelacionadas;
    }
    public void setTransacoesBancariasRelacionadas(List<TransacaoBancaria> transacoesBancariasRelacionadas) {
        this.transacoesBancariasRelacionadas = transacoesBancariasRelacionadas;
    }

}
