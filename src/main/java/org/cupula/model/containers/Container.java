package org.cupula.model.containers;

import org.cupula.model.containers.enums.ContainerTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Container extends EntityClass {
    
    private ContainerTipo containerTipo;
    private Long capacidadeMaximaEspaco;
    private Long pesoMaximo;
}
