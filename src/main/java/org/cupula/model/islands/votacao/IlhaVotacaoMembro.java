package org.cupula.model.islands.votacao;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaVotacaoMembro extends EntityClass {
    
    private IlhaVotacao votacao;
    private Long idMembroHash;
    private Boolean voto;

}
