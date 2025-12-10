package org.cupula.model.items.tipositem;

import java.util.List;

import org.cupula.model.containers.Container;
import org.cupula.model.items.Item;
import org.cupula.model.items.enums.EntityCompativel;

import jakarta.persistence.Entity;

@Entity
public class Equipamento extends Item{
    
    private List<Container> containers;
    private List<EntityCompativel> entitiesCompativeis;

    public List<Container> getContainers() {
        return containers;
    }
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
    public List<EntityCompativel> getEntitiesCompativeis() {
        return entitiesCompativeis;
    }
    public void setEntitiesCompativeis(List<EntityCompativel> entitiesCompativeis) {
        this.entitiesCompativeis = entitiesCompativeis;
    }

}
