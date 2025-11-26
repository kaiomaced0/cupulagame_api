package org.cupula.model.guilda;

import org.cupula.model.entities.Player;
import org.cupula.model.guilda.enums.CargoGuilda;

public class GuildaMembro {
    
    private Guilda guilda;
    private Player membro;

    private CargoGuilda cargo;

    private Long dataEntrada;
    private Long dataUltimaAtividade;
    private Boolean isOnline;

    private Long minutosDisponiveisParaGuildaSemanalmente;

    private String apelido;
}
