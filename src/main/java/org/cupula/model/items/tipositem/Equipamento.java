package org.cupula.model.items.tipositem;

import java.util.List;

import org.cupula.model.containers.Container;
import org.cupula.model.items.Item;
import org.cupula.model.items.enums.EntityCompativel;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity(name = "equipamento")
public class Equipamento extends Item{
    
    @OneToMany
    @JoinColumn(name = "equipamento_id")
    private List<Container> containers;

    @ElementCollection(targetClass = EntityCompativel.class)
    @CollectionTable(
        name = "equipamento_entity_compativel",
        joinColumns = @JoinColumn(name = "equipamento_id")
    )
    @Column(name = "entity_compativel")
    @Enumerated(EnumType.STRING)
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
