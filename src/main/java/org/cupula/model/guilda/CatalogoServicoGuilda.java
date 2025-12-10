package org.cupula.model.guilda;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CatalogoServicoGuilda extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;

    private String titulo;
    
    private String descricao;

    private Long price;

    private Long duracaoServicoEmMinutos;

    private Long tempoLimiteParaResponderSolicitacaoEmMinutos;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDuracaoServicoEmMinutos() {
        return duracaoServicoEmMinutos;
    }

    public void setDuracaoServicoEmMinutos(Long duracaoServicoEmMinutos) {
        this.duracaoServicoEmMinutos = duracaoServicoEmMinutos;
    }

    public Long getTempoLimiteParaResponderSolicitacaoEmMinutos() {
        return tempoLimiteParaResponderSolicitacaoEmMinutos;
    }

    public void setTempoLimiteParaResponderSolicitacaoEmMinutos(Long tempoLimiteParaResponderSolicitacaoEmMinutos) {
        this.tempoLimiteParaResponderSolicitacaoEmMinutos = tempoLimiteParaResponderSolicitacaoEmMinutos;
    }

    
}
