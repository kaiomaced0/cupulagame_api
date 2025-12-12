package org.cupula.model.islands.cargos;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaDepartamentoMembro extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "ilha_departamento_id")
    private IlhaDepartamento departamento;
    @ManyToOne
    @JoinColumn(name = "ilha_membro_id")
    private IlhaMembro representanteAtual;

    public IlhaDepartamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(IlhaDepartamento departamento) {
        this.departamento = departamento;
    }
    public IlhaMembro getRepresentanteAtual() {
        return representanteAtual;
    }
    public void setRepresentanteAtual(IlhaMembro representanteAtual) {
        this.representanteAtual = representanteAtual;
    }

}
