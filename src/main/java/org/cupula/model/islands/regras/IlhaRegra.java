package org.cupula.model.islands.regras;

import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.enums.IlhaRegrasTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaRegra extends EntityClass {

    private Ilha ilha;
    private Boolean ativa;
    private IlhaRegrasTipo tipo;

    private String titulo;
    private String descricao;

    private String penalidade;
    
}
