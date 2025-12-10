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
    
}
