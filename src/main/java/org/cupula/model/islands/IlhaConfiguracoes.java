package org.cupula.model.islands;

import org.cupula.model.EntityClass;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaConfiguracoes extends EntityClass {

    @ManyToOne
    private Ilha ilha;
    private Long impostoResidente;
    private Long impostoNaoResidente;
    private Long taxaEntrada;
    

}
