package org.cupula.model.guilda;

import org.cupula.model.entities.Player;
import org.cupula.model.guilda.enums.ContratoGuildaMembroTipoParticipante;

public class ContratoGuildaParticipante {
    
    private ContratoGuilda contratoGuilda;
    private Player participante;
    private ContratoGuildaMembroTipoParticipante tipoParticipante;
    private Long quantasMoedasGanha;
    private Long quantoXpGanha;
    private Long quantoXpGuildaGanha;
    private Boolean confirmado;
}
