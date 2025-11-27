package org.cupula.model.islands.votacao;

import java.time.LocalDateTime;
import java.util.List;

import org.cupula.model.islands.votacao.enums.IlhaVotacaoStatus;
import org.cupula.model.islands.votacao.enums.IlhaVotacaoTipo;

public class IlhaVotacao {
    
    private List<IlhaVotacaoMembro> votos;
    private LocalDateTime dataInicioVotacao;
    private LocalDateTime dataFimVotacao;
    private IlhaVotacaoStatus status;
    private String titulo;
    private String descricao;
    private IlhaVotacaoTipo tipo;
    
}
