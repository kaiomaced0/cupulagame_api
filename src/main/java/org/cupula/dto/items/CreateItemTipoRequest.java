package org.cupula.dto.items;

public record CreateItemTipoRequest(
    String nome,
    String descricao,
    Long limiteQuantidade,
    Long pesoPorQuantidade
) {}
