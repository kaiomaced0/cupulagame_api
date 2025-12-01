package org.cupula.model.structures.acesso;

import java.time.LocalDateTime;

import org.cupula.model.entities.Player;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class StructureAcesso extends EntityClass {
    
    private Player player;
    private Boolean acessoLiberado;
    private Boolean tempoRestrito;
    private LocalDateTime dataLimiteAcesso;

}
