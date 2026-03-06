package org.cupula.dto.items;

import java.time.LocalDateTime;

import org.cupula.model.items.ItemTipo;

public record ItemTipoResponse(
    Long id,
    String nome,
    String descricao,
    Long limiteQuantidade,
    Long pesoPorQuantidade,
    Boolean ativo,
    LocalDateTime dataInclusao
) {
    public static ItemTipoResponse fromEntity(ItemTipo entity) {
        return new ItemTipoResponse(
            entity.getId(),
            entity.getNome(),
            entity.getDescricao(),
            entity.getLimiteQuantidade(),
            entity.getPesoPorQuantidade(),
            entity.getAtivo(),
            entity.getDataInclusao()
        );
    }
}
