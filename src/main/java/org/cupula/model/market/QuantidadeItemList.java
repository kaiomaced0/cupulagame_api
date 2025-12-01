package org.cupula.model.market;

import org.cupula.model.items.ItemTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class QuantidadeItemList extends EntityClass {
    
    private ItemTipo itemTipo;
    private int quantidade;
}
