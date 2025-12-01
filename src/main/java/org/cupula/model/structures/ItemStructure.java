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
    
}
