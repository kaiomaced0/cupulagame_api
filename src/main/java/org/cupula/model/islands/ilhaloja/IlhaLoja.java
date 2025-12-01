package org.cupula.model.islands.ilhaloja;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.ItemStructure;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class IlhaLoja extends EntityClass {
    
    @ManyToOne
    private Ilha ilha;
    
    private String nome;
    private String descricao;
    private String horarioFuncionamento;
    
    @ManyToOne
    private Structure estruturaLoja;
    
    @OneToMany
    private List<ItemStructure> parteDaEstruturaQueAbreALoja;

    // Getters e Setters
    public Ilha getIlha() {
        return ilha;
    }

    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public Structure getEstruturaLoja() {
        return estruturaLoja;
    }

    public void setEstruturaLoja(Structure estruturaLoja) {
        this.estruturaLoja = estruturaLoja;
    }

    public List<ItemStructure> getParteDaEstruturaQueAbreALoja() {
        return parteDaEstruturaQueAbreALoja;
    }

    public void setParteDaEstruturaQueAbreALoja(List<ItemStructure> parteDaEstruturaQueAbreALoja) {
        this.parteDaEstruturaQueAbreALoja = parteDaEstruturaQueAbreALoja;
    }
}
