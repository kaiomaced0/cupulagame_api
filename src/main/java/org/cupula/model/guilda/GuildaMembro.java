package org.cupula.model.guilda;

import org.cupula.model.guilda.enums.CargoGuilda;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

@Entity
public class GuildaMembro extends EntityClass {
    
    private Guilda guilda;
    private Player membro;

    private CargoGuilda cargo;

    private Long dataEntrada;
    private Long dataUltimaAtividade;
    private Boolean isOnline;

    private Long minutosDisponiveisParaGuildaSemanalmente;

    private String apelido;
}
