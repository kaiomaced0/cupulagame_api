package org.cupula.dto.banco.response;

import java.time.LocalDateTime;

public record TransacaoSimplificadaResponse(
    Long id,
    String tipo, // "DEBITO" ou "CREDITO"
    Long valor,
    LocalDateTime data,
    String numeroOutraConta,
    String nomeOutraConta
) {}
