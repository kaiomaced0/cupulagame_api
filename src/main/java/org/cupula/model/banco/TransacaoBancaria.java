package org.cupula.model.banco;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class TransacaoBancaria extends EntityClass {
    
    private ContaBancaria contaOrigem;
    private ContaBancaria contaDestino;
    
    private Long valor;

    private Long contaBancariaOrigemAntes;
    private Long contaBancariaDestinoAntes;

    private Long contaBancariaOrigemDepois;
    private Long contaBancariaDestinoDepois;
    public ContaBancaria getContaOrigem() {
        return contaOrigem;
    }
    public void setContaOrigem(ContaBancaria contaOrigem) {
        this.contaOrigem = contaOrigem;
    }
    public ContaBancaria getContaDestino() {
        return contaDestino;
    }
    public void setContaDestino(ContaBancaria contaDestino) {
        this.contaDestino = contaDestino;
    }
    public Long getValor() {
        return valor;
    }
    public void setValor(Long valor) {
        this.valor = valor;
    }
    public Long getContaBancariaOrigemAntes() {
        return contaBancariaOrigemAntes;
    }
    public void setContaBancariaOrigemAntes(Long contaBancariaOrigemAntes) {
        this.contaBancariaOrigemAntes = contaBancariaOrigemAntes;
    }
    public Long getContaBancariaDestinoAntes() {
        return contaBancariaDestinoAntes;
    }
    public void setContaBancariaDestinoAntes(Long contaBancariaDestinoAntes) {
        this.contaBancariaDestinoAntes = contaBancariaDestinoAntes;
    }
    public Long getContaBancariaOrigemDepois() {
        return contaBancariaOrigemDepois;
    }
    public void setContaBancariaOrigemDepois(Long contaBancariaOrigemDepois) {
        this.contaBancariaOrigemDepois = contaBancariaOrigemDepois;
    }
    public Long getContaBancariaDestinoDepois() {
        return contaBancariaDestinoDepois;
    }
    public void setContaBancariaDestinoDepois(Long contaBancariaDestinoDepois) {
        this.contaBancariaDestinoDepois = contaBancariaDestinoDepois;
    }

}
