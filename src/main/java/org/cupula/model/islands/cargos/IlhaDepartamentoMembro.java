package org.cupula.model.islands.cargos;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaDepartamentoMembro extends EntityClass {
    
    @ManyToOne
    private IlhaDepartamento departamento;
    @ManyToOne
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
