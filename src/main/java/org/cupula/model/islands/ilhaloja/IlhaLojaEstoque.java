package org.cupula.model.islands.ilhaloja;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.items.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class IlhaLojaEstoque extends EntityClass {
    
    // tambem usado para o player fornecer para a loja
    @ManyToOne
    private IlhaLoja loja;
    
    private int quantidadeAtual;
    private int quantidadeMaxima;
    private Long precoCompra;
    private String titulo;
    private String descricao;
    private String pathImagem;
    
    @OneToMany
    private List<Item> itensDisponiveis;

    // Getters e Setters
    public IlhaLoja getLoja() {
        return loja;
    }

    public void setLoja(IlhaLoja loja) {
        this.loja = loja;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public Long getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Long precoCompra) {
        this.precoCompra = precoCompra;
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

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public List<Item> getItensDisponiveis() {
        return itensDisponiveis;
    }

    public void setItensDisponiveis(List<Item> itensDisponiveis) {
        this.itensDisponiveis = itensDisponiveis;
    }
}
