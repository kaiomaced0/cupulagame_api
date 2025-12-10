package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.structures.enums.ItemStructureTipo;
import org.cupula.model.structures.enums.Layer;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ItemStructure extends EntityClass {

    private List<ItemStructurePart> parts;
    private Layer layer;
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
