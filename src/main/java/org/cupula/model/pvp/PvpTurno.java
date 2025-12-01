package org.cupula.model.pvp;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class PvpTurno extends EntityClass {
    
    @ManyToOne
    private Pvp pvp;
    
    private Integer numero;
    
    // Getters e Setters
    public Pvp getPvp() {
        return pvp;
    }

    public void setPvp(Pvp pvp) {
        this.pvp = pvp;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
