package org.cupula.dto.banco.response;

import java.time.LocalDateTime;

public record TransacaoDetalhadaResponse(
    Long id,
    Long valor,
    LocalDateTime dataTransacao,
    
    // Conta Origem
    Long contaOrigemId,
    String numeroContaOrigem,
    String titularContaOrigem,
    Long saldoOrigemAntes,
    Long saldoOrigemDepois,
    
    // Conta Destino
    Long contaDestinoId,
    String numeroContaDestino,
    String titularContaDestino,
    Long saldoDestinoAntes,
    Long saldoDestinoDepois
) {}
