package org.cupula.model.islands.votacao;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "ilha_votacao_membro")
public class IlhaVotacaoMembro extends EntityClass {
    
    @ManyToOne
    private IlhaVotacao votacao;
    private Long idMembroHash;
    private Boolean voto;

    public IlhaVotacao getVotacao() {
        return votacao;
    }
    public void setVotacao(IlhaVotacao votacao) {
        this.votacao = votacao;
    }
    public Long getIdMembroHash() {
        return idMembroHash;
    }
    public void setIdMembroHash(Long idMembroHash) {
        this.idMembroHash = idMembroHash;
    }
    public Boolean getVoto() {
        return voto;
    }
    public void setVoto(Boolean voto) {
        this.voto = voto;
    }

}
