package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.containers.Container;
import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.enums.StructureTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Structure extends EntityClass {

    @OneToMany
    private List<ItemStructure> itemStructures;
    @OneToMany
    private List<Container> containers;
    @Enumerated(EnumType.STRING)
    private StructureTipo tipo;
    @ManyToOne
    private Ilha ilha;
    
}
