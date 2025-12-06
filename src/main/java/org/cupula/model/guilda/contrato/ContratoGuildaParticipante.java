package org.cupula.model.guilda.contrato;

import org.cupula.model.guilda.enums.ContratoGuildaMembroTipoParticipante;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

@Entity
public class ContratoGuildaParticipante extends EntityClass {
    
    private ContratoGuilda contratoGuilda;
    private Player participante;
    private ContratoGuildaMembroTipoParticipante tipoParticipante;
    private Long quantasMoedasGanha;
    private Long quantoXpGanha;
    private Long quantoXpGuildaGanha;
    private Boolean confirmado;
}
