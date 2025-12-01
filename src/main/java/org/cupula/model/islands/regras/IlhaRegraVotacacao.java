package org.cupula.model.islands.regras;

import org.cupula.model.islands.votacao.IlhaVotacao;

import jakarta.persistence.Entity;

@Entity
public class IlhaRegraVotacacao extends IlhaVotacao {
    
    private IlhaRegra ilhaRegra;
}
