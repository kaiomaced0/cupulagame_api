package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.structures.enums.ItemStructureTipo;
import org.cupula.model.structures.enums.Layer;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;

import org.cupula.model.EntityClass;

import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;

@Entity
public class ItemStructure extends EntityClass {

    @OneToMany
    @JoinColumn(name = "item_structure_id")
    private List<ItemStructurePart> parts;

    @Enumerated(EnumType.STRING)
    private Layer layer;
    
    @Enumerated(EnumType.STRING)
    private ItemStructureTipo tipo;
    
    public List<ItemStructurePart> getParts() {
        return parts;
    }
    public void setParts(List<ItemStructurePart> parts) {
        this.parts = parts;
    }
    public Layer getLayer() {
        return layer;
    }
    public void setLayer(Layer layer) {
        this.layer = layer;
    }
    public ItemStructureTipo getTipo() {
        return tipo;
    }
    public void setTipo(ItemStructureTipo tipo) {
        this.tipo = tipo;
    }

}
