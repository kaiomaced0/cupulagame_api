package org.cupula.model.containers;

import org.cupula.model.containers.enums.ContainerTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

import org.cupula.model.EntityClass;

import jakarta.persistence.EnumType;

@Entity
public class Container extends EntityClass {
    
    @Enumerated(EnumType.STRING)
    private ContainerTipo containerTipo;

    private Long capacidadeMaximaEspaco;
    private Long pesoMaximo;
    
    public ContainerTipo getContainerTipo() {
        return containerTipo;
    }
    public void setContainerTipo(ContainerTipo containerTipo) {
        this.containerTipo = containerTipo;
    }
    public Long getCapacidadeMaximaEspaco() {
        return capacidadeMaximaEspaco;
    }
    public void setCapacidadeMaximaEspaco(Long capacidadeMaximaEspaco) {
        this.capacidadeMaximaEspaco = capacidadeMaximaEspaco;
    }
    public Long getPesoMaximo() {
        return pesoMaximo;
    }
    public void setPesoMaximo(Long pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

}
