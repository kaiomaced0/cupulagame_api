package org.cupula.model.islands.cargos;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaDepartamentoMembro extends EntityClass {
    
    private IlhaDepartamento departamento;
    private IlhaMembro representanteAtual;

}
