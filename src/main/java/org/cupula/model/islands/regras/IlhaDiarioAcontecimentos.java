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
}
