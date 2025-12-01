package org.cupula.model.pvp.arenas;

import org.cupula.model.EntityClass;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ArenaPvpLocalFuga extends EntityClass {
    // a ideia é que seja um local em um canto do mapa que o player possa fugir, ele tem que estar encima da
    // estrutura e realizar dois movimentos para "fora" do mapa para fugir da arena
    
    @ManyToOne
    private Structure estrutura;
    
    private Long x;
    private Long y;
    
    @ManyToOne
    private ArenaPvp arena;
    
    // Getters e Setters
    public Structure getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(Structure estrutura) {
        this.estrutura = estrutura;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public ArenaPvp getArena() {
        return arena;
    }

    public void setArena(ArenaPvp arena) {
        this.arena = arena;
    }
}
