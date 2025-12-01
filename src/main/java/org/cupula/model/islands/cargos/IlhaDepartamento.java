package org.cupula.model.islands.cargos;

import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.enums.IlhaCargo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaDepartamento extends EntityClass {
    
    private Ilha ilha;
    private String nomeCargo;

    private IlhaCargo cargo;

    // use tambem a ilhadepartamentorepresentante para acompanhar o historico de representantes

    private Long salarioRepresentante;
    private Long verbaDepartamentoMesAtual;

    private Long saldoConta;

    private Long limiteMembros; // limite de membros para este cargo!

}
