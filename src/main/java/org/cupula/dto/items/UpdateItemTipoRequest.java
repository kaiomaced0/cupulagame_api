package org.cupula.dto.items;

public record UpdateItemTipoRequest(
    String nome,
    String descricao,
    Long limiteQuantidade,
    Long pesoPorQuantidade
) {}
