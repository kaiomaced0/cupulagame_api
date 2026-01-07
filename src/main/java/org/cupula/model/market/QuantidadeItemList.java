package org.cupula.model.market;

import org.cupula.model.items.ItemTipo;

import jakarta.persistence.Entity;

import org.cupula.model.EntityClass;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "quantidade_item_list")
public class QuantidadeItemList extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "item_tipo_id")
    private ItemTipo itemTipo;

    private int quantidade;
    
    public ItemTipo getItemTipo() {
        return itemTipo;
    }
    public void setItemTipo(ItemTipo itemTipo) {
        this.itemTipo = itemTipo;
    }

}
