package org.cupula.model.pvp;

import org.cupula.model.EntityClass;
import org.cupula.model.pvp.enums.PvpAcaoTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity(name = "pvp_acao")
public class PvpAcao extends EntityClass {
    
    @Enumerated(EnumType.STRING)
    private PvpAcaoTipo tipo;
    
    private Long xAlvo;
    private Long yAlvo;
    
    @ManyToOne
    private PvpMovimento movimento;
    
    // Getters e Setters
    public PvpAcaoTipo getTipo() {
        return tipo;
    }

    public void setTipo(PvpAcaoTipo tipo) {
        this.tipo = tipo;
    }

    public Long getxAlvo() {
        return xAlvo;
    }

    public void setxAlvo(Long xAlvo) {
        this.xAlvo = xAlvo;
    }

    public Long getyAlvo() {
        return yAlvo;
    }

    public void setyAlvo(Long yAlvo) {
        this.yAlvo = yAlvo;
    }

    public PvpMovimento getMovimento() {
        return movimento;
    }

    public void setMovimento(PvpMovimento movimento) {
        this.movimento = movimento;
    }
    public Long getXAlvo() {
        return xAlvo;
    }
    public void setXAlvo(Long xAlvo) {
        this.xAlvo = xAlvo;
    }
    public Long getYAlvo() {
        return yAlvo;
    }
    public void setYAlvo(Long yAlvo) {
        this.yAlvo = yAlvo;
    }

}
