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
}
