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
    
    public List<IlhaVotacaoMembro> getVotos() {
        return votos;
    }
    public void setVotos(List<IlhaVotacaoMembro> votos) {
        this.votos = votos;
    }
    public LocalDateTime getDataInicioVotacao() {
        return dataInicioVotacao;
    }
    public void setDataInicioVotacao(LocalDateTime dataInicioVotacao) {
        this.dataInicioVotacao = dataInicioVotacao;
    }
    public LocalDateTime getDataFimVotacao() {
        return dataFimVotacao;
    }
    public void setDataFimVotacao(LocalDateTime dataFimVotacao) {
        this.dataFimVotacao = dataFimVotacao;
    }
    public IlhaVotacaoStatus getStatus() {
        return status;
    }
    public void setStatus(IlhaVotacaoStatus status) {
        this.status = status;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public IlhaVotacaoTipo getTipo() {
        return tipo;
    }
    public void setTipo(IlhaVotacaoTipo tipo) {
        this.tipo = tipo;
    }

}
