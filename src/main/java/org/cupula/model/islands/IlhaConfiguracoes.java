package org.cupula.model.islands;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "ilha_configuracoes")
public class IlhaConfiguracoes extends EntityClass {

    @ManyToOne
    private Ilha ilha;
    private Long impostoResidente;
    private Long impostoNaoResidente;
    private Long taxaEntrada;
    

    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }
    public Long getImpostoResidente() {
        return impostoResidente;
    }
    public void setImpostoResidente(Long impostoResidente) {
        this.impostoResidente = impostoResidente;
    }
    public Long getImpostoNaoResidente() {
        return impostoNaoResidente;
    }
    public void setImpostoNaoResidente(Long impostoNaoResidente) {
        this.impostoNaoResidente = impostoNaoResidente;
    }
    public Long getTaxaEntrada() {
        return taxaEntrada;
    }
    public void setTaxaEntrada(Long taxaEntrada) {
        this.taxaEntrada = taxaEntrada;
    }

}
