package org.cupula.model.pvp;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;
import org.cupula.model.entities.Player;
import org.cupula.model.pvp.enums.PvpAcaoTipo;

@Entity
public class PvpAcao extends EntityClass {
    private PvpAcaoTipo tipo;
    private Pvp pvp;
    private Player player;
    
}
