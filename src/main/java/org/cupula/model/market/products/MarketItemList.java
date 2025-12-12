package org.cupula.model.market.products;

import org.cupula.model.items.ItemTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.cupula.model.EntityClass;

@Entity
public class MarketItemList extends EntityClass {
    
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
