package org.cupula.model.entities;

import org.cupula.model.EntityClass;

@jakarta.persistence.Entity
public class Entity extends EntityClass {

    private Long inicioX;
    private Long inicioY;
    private Long fimX;
    private Long fimY;
    private Long inicioZ;
    private Long fimZ;
    
    public Long getInicioX() {
        return inicioX;
    }
    public void setInicioX(Long inicioX) {
        this.inicioX = inicioX;
    }
    public Long getInicioY() {
        return inicioY;
    }
    public void setInicioY(Long inicioY) {
        this.inicioY = inicioY;
    }
    public Long getFimX() {
        return fimX;
    }
    public void setFimX(Long fimX) {
        this.fimX = fimX;
    }
    public Long getFimY() {
        return fimY;
    }
    public void setFimY(Long fimY) {
        this.fimY = fimY;
    }
    public Long getInicioZ() {
        return inicioZ;
    }
    public void setInicioZ(Long inicioZ) {
        this.inicioZ = inicioZ;
    }
    public Long getFimZ() {
        return fimZ;
    }
    public void setFimZ(Long fimZ) {
        this.fimZ = fimZ;
    }

}
