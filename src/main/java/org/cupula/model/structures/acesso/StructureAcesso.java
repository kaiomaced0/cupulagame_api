package org.cupula.model.structures.acesso;

import java.time.LocalDateTime;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;

@Entity
public class StructureAcesso extends EntityClass {
    
    private Player player;
    private Boolean acessoLiberado;
    private Boolean tempoRestrito;
    private LocalDateTime dataLimiteAcesso;

}
