package org.cupula.model.islands.regras;

import org.cupula.model.islands.votacao.IlhaVotacao;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaRegraVotacacao extends IlhaVotacao {
    
    @ManyToOne
    private IlhaRegra ilhaRegra;
    public IlhaRegra getIlhaRegra() {
        return ilhaRegra;
    }
    public void setIlhaRegra(IlhaRegra ilhaRegra) {
        this.ilhaRegra = ilhaRegra;
    }

}
