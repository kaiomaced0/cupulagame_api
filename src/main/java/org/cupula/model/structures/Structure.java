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
    
    public List<ItemStructure> getItemStructures() {
        return itemStructures;
    }
    public void setItemStructures(List<ItemStructure> itemStructures) {
        this.itemStructures = itemStructures;
    }
    public List<Container> getContainers() {
        return containers;
    }
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
    public StructureTipo getTipo() {
        return tipo;
    }
    public void setTipo(StructureTipo tipo) {
        this.tipo = tipo;
    }
    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }

}
