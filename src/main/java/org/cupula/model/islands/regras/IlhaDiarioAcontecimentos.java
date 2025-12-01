package org.cupula.model.islands.regras;

import org.cupula.model.islands.enums.IlhaDiarioAcontecimentosTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaDiarioAcontecimentos extends EntityClass {
    
    private IlhaDiario diario;
    private String acontecimento;
    private Long dataAcontecimento;
    private IlhaRegra ilhaRegraReferenteAoAcontecido;
    private IlhaDiarioAcontecimentosTipo tipo;
}
