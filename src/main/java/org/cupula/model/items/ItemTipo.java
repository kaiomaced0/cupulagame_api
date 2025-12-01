package org.cupula.model.items;

import java.util.List;

import org.cupula.model.items.enums.ItemTipoTag;
import org.cupula.model.structures.ItemStructure;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ItemTipo extends EntityClass {

    private String nome;
    private String descricao;

    private Long limiteQuantidade;
    private Long pesoPorQuantidade;

    private ItemStructure itemStructureBase;
    private List<ItemTipoTag> tagsBase;
}
