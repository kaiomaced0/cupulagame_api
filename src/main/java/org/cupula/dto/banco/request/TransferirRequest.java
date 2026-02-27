package org.cupula.dto.banco.request;

public record TransferirRequest(
    String numeroContaDestino,
    Long valor,
    String senha
) {}
