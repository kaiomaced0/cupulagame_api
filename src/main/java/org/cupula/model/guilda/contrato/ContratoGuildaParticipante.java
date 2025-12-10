package org.cupula.model.guilda.contrato;

import org.cupula.model.EntityClass;
import org.cupula.model.banco.TransacaoBancaria;
import org.cupula.model.entities.player.Player;
import org.cupula.model.guilda.enums.ContratoGuildaMembroTipoParticipante;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ContratoGuildaParticipante extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "contrato_guilda_id")
    private ContratoGuilda contratoGuilda;
    @ManyToOne
    @JoinColumn(name = "participante_id")
    private Player participante;

    @Enumerated(EnumType.STRING)
    private ContratoGuildaMembroTipoParticipante tipoParticipante;

    private Long quantasMoedasGanha;
    private Long quantoXpGanha;
    private Long quantoXpGuildaGanha;
    private Boolean confirmado;
    private Boolean pago;

    @OneToOne
    @JoinColumn(name = "transacao_bancaria_id")
    private TransacaoBancaria transacaoBancariaRelacionada;
    
    public ContratoGuilda getContratoGuilda() {
        return contratoGuilda;
    }
    public void setContratoGuilda(ContratoGuilda contratoGuilda) {
        this.contratoGuilda = contratoGuilda;
    }
    public Player getParticipante() {
        return participante;
    }
    public void setParticipante(Player participante) {
        this.participante = participante;
    }
    public ContratoGuildaMembroTipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }
    public void setTipoParticipante(ContratoGuildaMembroTipoParticipante tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }
    public Long getQuantasMoedasGanha() {
        return quantasMoedasGanha;
    }
    public void setQuantasMoedasGanha(Long quantasMoedasGanha) {
        this.quantasMoedasGanha = quantasMoedasGanha;
    }
    public Long getQuantoXpGanha() {
        return quantoXpGanha;
    }
    public void setQuantoXpGanha(Long quantoXpGanha) {
        this.quantoXpGanha = quantoXpGanha;
    }
    public Long getQuantoXpGuildaGanha() {
        return quantoXpGuildaGanha;
    }
    public void setQuantoXpGuildaGanha(Long quantoXpGuildaGanha) {
        this.quantoXpGuildaGanha = quantoXpGuildaGanha;
    }
    public Boolean getConfirmado() {
        return confirmado;
    }
    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }
    public Boolean getPago() {
        return pago;
    }
    public void setPago(Boolean pago) {
        this.pago = pago;
    }
    public TransacaoBancaria getTransacaoBancariaRelacionada() {
        return transacaoBancariaRelacionada;
    }
    public void setTransacaoBancariaRelacionada(TransacaoBancaria transacaoBancariaRelacionada) {
        this.transacaoBancariaRelacionada = transacaoBancariaRelacionada;
    }

}
