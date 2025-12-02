package org.cupula.model.islands.votacao;

import java.time.LocalDateTime;
import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.votacao.enums.IlhaVotacaoStatus;
import org.cupula.model.islands.votacao.enums.IlhaVotacaoTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

@Entity
public class IlhaVotacao extends EntityClass {
    
    @OneToMany
    private List<IlhaVotacaoMembro> votos;
    private LocalDateTime dataInicioVotacao;
    private LocalDateTime dataFimVotacao;
    @Enumerated(EnumType.STRING)
    private IlhaVotacaoStatus status;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private IlhaVotacaoTipo tipo;
    
}
