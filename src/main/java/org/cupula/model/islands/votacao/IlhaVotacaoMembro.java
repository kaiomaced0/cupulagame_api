package org.cupula.model.islands.votacao;

import org.cupula.model.EntityClass;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaVotacaoMembro extends EntityClass {
    
    @ManyToOne
    private IlhaVotacao votacao;
    private Long idMembroHash;
    private Boolean voto;

}
