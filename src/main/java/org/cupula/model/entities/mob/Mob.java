package org.cupula.model.entities.mob;

import java.util.List;

import org.cupula.model.containers.Container;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import jakarta.persistence.EnumType;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.MobTipo;

@Entity(name = "mob")
public class Mob extends EntityClass {
    
    @OneToMany
    @JoinColumn(name = "mob_id")
    private List<Container> containers;

    @OneToOne
    @JoinColumn(name = "posicao_id")
    private MobPosicao posicao;
    
    @Enumerated(EnumType.STRING)
    private MobTipo tipo;

    public List<Container> getContainers() {
        return containers;
    }
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
    public MobPosicao getPosicao() {
        return posicao;
    }
    public void setPosicao(MobPosicao posicao) {
        this.posicao = posicao;
    }
    public MobTipo getTipo() {
        return tipo;
    }
    public void setTipo(MobTipo tipo) {
        this.tipo = tipo;
    }

}
