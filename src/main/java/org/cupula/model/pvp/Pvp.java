package org.cupula.model.pvp;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;
import org.cupula.model.pvp.enums.PvpTipo;

@Entity
public class Pvp extends EntityClass {
    private PvpTipo tipo;
    
    public PvpTipo getTipo() {
        return tipo;
    }
    public void setTipo(PvpTipo tipo) {
        this.tipo = tipo;
    }

}
