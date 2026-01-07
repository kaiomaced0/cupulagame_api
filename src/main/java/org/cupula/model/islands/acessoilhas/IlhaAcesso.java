package org.cupula.model.islands.acessoilhas;

import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.Structure;

import org.cupula.model.EntityClass;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ilha_acesso")
public class IlhaAcesso extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "ilha_origem_id")
    private Ilha ilhaOrigem;
    
    @ManyToOne
    @JoinColumn(name = "ilha_destino_id")
    private Ilha ilhaDestino;
    
    @ManyToOne
    @JoinColumn(name = "estrutura_id")
    private Structure estrutura;

    private Long tempoViagem;    
    
    public Ilha getIlhaOrigem() {
        return ilhaOrigem;
    }
    public void setIlhaOrigem(Ilha ilhaOrigem) {
        this.ilhaOrigem = ilhaOrigem;
    }
    public Ilha getIlhaDestino() {
        return ilhaDestino;
    }
    public void setIlhaDestino(Ilha ilhaDestino) {
        this.ilhaDestino = ilhaDestino;
    }
    public Structure getEstrutura() {
        return estrutura;
    }
    public void setEstrutura(Structure estrutura) {
        this.estrutura = estrutura;
    }
    public Long getTempoViagem() {
        return tempoViagem;
    }
    public void setTempoViagem(Long tempoViagem) {
        this.tempoViagem = tempoViagem;
    }

}
