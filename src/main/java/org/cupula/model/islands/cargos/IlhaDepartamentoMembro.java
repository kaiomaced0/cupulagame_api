package org.cupula.model.islands.cargos;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaDepartamentoMembro extends EntityClass {
    
    @ManyToOne
    private IlhaDepartamento departamento;
    @ManyToOne
    private IlhaMembro representanteAtual;

}
