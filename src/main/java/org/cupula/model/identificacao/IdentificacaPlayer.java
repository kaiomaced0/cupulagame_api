package org.cupula.model.identificacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.Player;

import jakarta.persistence.Entity;

@Entity
public class IdentificacaPlayer extends EntityClass {
    
    private Player player;
    private Identificacao identificacao;
}
