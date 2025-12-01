package org.cupula.model.identificacao;

import org.cupula.model.EntityClass;
import org.cupula.model.identificacao.enums.IdentificacaoTemporariaTipo;
import org.cupula.model.identificacao.enums.IdentificacaoTipo;

import jakarta.persistence.Entity;

@Entity
public class Identificacao extends EntityClass {
    
    private String nome;
    private IdentificacaoTipo tipo;
    private IdentificacaoTemporariaTipo identificacaoTemporariaTipo;
    private String codigo;
    private Long numeroIdentificacao;
    private Boolean valida;

    }
