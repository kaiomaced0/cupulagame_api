package org.cupula.model.market.products;

import org.cupula.model.items.ItemTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class MarketItemList extends EntityClass {
    
    private ItemTipo itemTipo;
    private int quantidade;
    public ItemTipo getItemTipo() {
        return itemTipo;
    }
    public void setItemTipo(ItemTipo itemTipo) {
        this.itemTipo = itemTipo;
    }

}
