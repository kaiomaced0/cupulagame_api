package org.cupula.model.islands.cargos;

import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.enums.IlhaCargo;

public class IlhaDepartamento {
    
    private Ilha ilha;
    private String nomeCargo;

    private IlhaCargo cargo;

    // use tambem a ilhadepartamentorepresentante para acompanhar o historico de representantes

    private Long salarioRepresentante;
    private Long verbaDepartamentoMesAtual;

    private Long saldoConta;

    private Long limiteMembros; // limite de membros para este cargo!

}
