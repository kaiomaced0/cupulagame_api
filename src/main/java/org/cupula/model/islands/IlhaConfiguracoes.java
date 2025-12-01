package org.cupula.model.islands;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaConfiguracoes extends EntityClass {

    private Ilha ilha;
    private Long impostoResidente;
    private Long impostoNaoResidente;
    private Long taxaEntrada;
    

}
