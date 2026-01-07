package org.cupula.model.islands.regras;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.enums.IlhaRegrasTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity(name = "ilha_regra")
public class IlhaRegra extends EntityClass {

    @ManyToOne
    private Ilha ilha;
    private Boolean ativa;
    @Enumerated(EnumType.STRING)
    private IlhaRegrasTipo tipo;

    private String titulo;
    private String descricao;

    private String penalidade;
    
    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }
    public Boolean getAtiva() {
        return ativa;
    }
    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
    public IlhaRegrasTipo getTipo() {
        return tipo;
    }
    public void setTipo(IlhaRegrasTipo tipo) {
        this.tipo = tipo;
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
    public String getPenalidade() {
        return penalidade;
    }
    public void setPenalidade(String penalidade) {
        this.penalidade = penalidade;
    }

}
