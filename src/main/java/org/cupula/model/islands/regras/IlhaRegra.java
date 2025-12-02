package org.cupula.model.islands.regras;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.enums.IlhaRegrasTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaRegra extends EntityClass {

    @ManyToOne
    private Ilha ilha;
    private Boolean ativa;
    @Enumerated(EnumType.STRING)
    private IlhaRegrasTipo tipo;

    private String titulo;
    private String descricao;

    private String penalidade;
    
}
