package org.cupula.model.identificacao;

import org.cupula.model.EntityClass;
import org.cupula.model.identificacao.enums.IdentificacaoTemporariaTipo;
import org.cupula.model.identificacao.enums.IdentificacaoTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Identificacao extends EntityClass {
    
    private String nome;
    
    @Enumerated(EnumType.STRING)
    private IdentificacaoTipo tipo;
    
    @Enumerated(EnumType.STRING)
    private IdentificacaoTemporariaTipo identificacaoTemporariaTipo;

    private String codigo;

    private Long numeroIdentificacao;

    private Boolean valida;

    }
