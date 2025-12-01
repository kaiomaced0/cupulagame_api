package org.cupula.model.islands.votacao;

import java.time.LocalDateTime;
import java.util.List;

import org.cupula.model.islands.votacao.enums.IlhaVotacaoStatus;
import org.cupula.model.islands.votacao.enums.IlhaVotacaoTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaVotacao extends EntityClass {
    
    private List<IlhaVotacaoMembro> votos;
    private LocalDateTime dataInicioVotacao;
    private LocalDateTime dataFimVotacao;
    private IlhaVotacaoStatus status;
    private String titulo;
    private String descricao;
    private IlhaVotacaoTipo tipo;
    
}
