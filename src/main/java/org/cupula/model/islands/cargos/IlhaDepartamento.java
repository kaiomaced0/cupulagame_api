package org.cupula.model.islands.cargos;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.enums.IlhaCargo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ilha_departamento")
public class IlhaDepartamento extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "ilha_id")
    private Ilha ilha;
    private String nomeCargo;

    @Enumerated(EnumType.STRING)
    private IlhaCargo cargo;

    // use tambem a ilhadepartamentorepresentante para acompanhar o historico de representantes

    private Long salarioRepresentante;
    private Long verbaDepartamentoMesAtual;

    private Long saldoConta;

    private Long limiteMembros; // limite de membros para este cargo!

    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }
    public String getNomeCargo() {
        return nomeCargo;
    }
    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
    public IlhaCargo getCargo() {
        return cargo;
    }
    public void setCargo(IlhaCargo cargo) {
        this.cargo = cargo;
    }
    public Long getSalarioRepresentante() {
        return salarioRepresentante;
    }
    public void setSalarioRepresentante(Long salarioRepresentante) {
        this.salarioRepresentante = salarioRepresentante;
    }
    public Long getVerbaDepartamentoMesAtual() {
        return verbaDepartamentoMesAtual;
    }
    public void setVerbaDepartamentoMesAtual(Long verbaDepartamentoMesAtual) {
        this.verbaDepartamentoMesAtual = verbaDepartamentoMesAtual;
    }
    public Long getSaldoConta() {
        return saldoConta;
    }
    public void setSaldoConta(Long saldoConta) {
        this.saldoConta = saldoConta;
    }
    public Long getLimiteMembros() {
        return limiteMembros;
    }
    public void setLimiteMembros(Long limiteMembros) {
        this.limiteMembros = limiteMembros;
    }

}
