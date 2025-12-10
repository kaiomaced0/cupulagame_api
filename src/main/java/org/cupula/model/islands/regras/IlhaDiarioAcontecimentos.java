package org.cupula.model.islands.regras;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.enums.IlhaDiarioAcontecimentosTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaDiarioAcontecimentos extends EntityClass {
    
    @ManyToOne
    private IlhaDiario diario;
    private String acontecimento;
    private Long dataAcontecimento;
    @ManyToOne
    private IlhaRegra ilhaRegraReferenteAoAcontecido;
    @Enumerated(EnumType.STRING)
    private IlhaDiarioAcontecimentosTipo tipo;
    public IlhaDiario getDiario() {
        return diario;
    }
    public void setDiario(IlhaDiario diario) {
        this.diario = diario;
    }
    public String getAcontecimento() {
        return acontecimento;
    }
    public void setAcontecimento(String acontecimento) {
        this.acontecimento = acontecimento;
    }
    public Long getDataAcontecimento() {
        return dataAcontecimento;
    }
    public void setDataAcontecimento(Long dataAcontecimento) {
        this.dataAcontecimento = dataAcontecimento;
    }
    public IlhaRegra getIlhaRegraReferenteAoAcontecido() {
        return ilhaRegraReferenteAoAcontecido;
    }
    public void setIlhaRegraReferenteAoAcontecido(IlhaRegra ilhaRegraReferenteAoAcontecido) {
        this.ilhaRegraReferenteAoAcontecido = ilhaRegraReferenteAoAcontecido;
    }
    public IlhaDiarioAcontecimentosTipo getTipo() {
        return tipo;
    }
    public void setTipo(IlhaDiarioAcontecimentosTipo tipo) {
        this.tipo = tipo;
    }

}
