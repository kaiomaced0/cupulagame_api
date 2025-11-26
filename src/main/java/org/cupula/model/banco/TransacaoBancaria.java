package org.cupula.model.banco;

public class TransacaoBancaria {
    
    private ContaBancaria contaOrigem;
    private ContaBancaria contaDestino;
    
    private Long valor;

    private Long contaBancariaOrigemAntes;
    private Long contaBancariaDestinoAntes;

    private Long contaBancariaOrigemDepois;
    private Long contaBancariaDestinoDepois;
}
