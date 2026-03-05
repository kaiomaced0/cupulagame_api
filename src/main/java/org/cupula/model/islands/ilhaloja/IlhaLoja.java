package org.cupula.model.islands.ilhaloja;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.StructureUnit;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "ilha_loja")
public class IlhaLoja extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "ilha_id")
    private Ilha ilha;
    
    private String nome;
    private String descricao;
    private String horarioFuncionamento;
    
    @OneToOne
    @JoinColumn(name = "structure_id")
    private Structure estruturaLoja;
    

    // essa é a area onde quando o player entra no "range" disponibiliza a tela da loja para ele acessar. (ao sair dessa area, a tela some)
    @OneToMany
    @JoinColumn(name = "ilha_loja_id")
    private List<StructureUnit> parteDaEstruturaQueAbreALoja;

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

    public List<StructureUnit> getParteDaEstruturaQueAbreALoja() {
        return parteDaEstruturaQueAbreALoja;
    }

    public void setParteDaEstruturaQueAbreALoja(List<StructureUnit> parteDaEstruturaQueAbreALoja) {
        this.parteDaEstruturaQueAbreALoja = parteDaEstruturaQueAbreALoja;
    }
}
