package org.cupula.model.structures;

import java.awt.Container;
import java.util.List;

import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.enums.StructureTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Structure extends EntityClass {

    private List<ItemStructure> itemStructures;
    private List<Container> containers;
    private StructureTipo tipo;
    private Ilha ilha;
    
}
