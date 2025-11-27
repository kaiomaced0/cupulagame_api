package org.cupula.model.islands.regras;

import org.cupula.model.islands.enums.IlhaDiarioAcontecimentosTipo;

public class IlhaDiarioAcontecimentos {
    
    private IlhaDiario diario;
    private String acontecimento;
    private Long dataAcontecimento;
    private IlhaRegra ilhaRegraReferenteAoAcontecido;
    private IlhaDiarioAcontecimentosTipo tipo;
}
