package org.cupula.dto.banco.response;

import java.util.List;

public record DadosBasicosContaResponse(
    Long contaId,
    String numeroConta,
    String titular,
    Long saldo,
    String tipo,
    List<TransacaoSimplificadaResponse> ultimasTransacoes
) {}
