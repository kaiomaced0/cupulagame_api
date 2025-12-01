package org.cupula.model.guilda;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class CatalogoServicoGuilda extends EntityClass {
    
    private String titulo;
    private String descricao;
    private Long price;
    private Long duracaoServicoEmMinutos;
    private Long tempoLimiteParaResponderSolicitacaoEmMinutos;

}
