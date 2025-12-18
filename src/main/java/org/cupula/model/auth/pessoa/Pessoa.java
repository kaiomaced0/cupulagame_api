package org.cupula.model.auth.pessoa;

import java.time.LocalDate;
import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.documento.Documento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Pessoa extends EntityClass {

    private String nomeCompleto;

    private String cpf;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToMany
    @JoinColumn(name = "pessoa_id")
    private List<Documento> listaDeDocumentos;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<Documento> getListaDeDocumentos() {
        return listaDeDocumentos;
    }

    public void setListaDeDocumentos(List<Documento> listaDeDocumentos) {
        this.listaDeDocumentos = listaDeDocumentos;
    }
}
