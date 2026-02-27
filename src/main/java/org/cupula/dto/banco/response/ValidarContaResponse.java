package org.cupula.dto.banco.response;

public record ValidarContaResponse(
    String numeroConta,
    String nomeUsuario,
    boolean contaExiste
) {}
