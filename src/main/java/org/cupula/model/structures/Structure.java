package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.auth.Usuario;
import org.cupula.model.containers.Container;
import org.cupula.model.islands.Ilha;
import org.cupula.model.pvp.arenas.ArenaPvp;
import org.cupula.model.structures.enums.StructureTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Structure extends EntityClass {

    @OneToMany
    @JoinColumn(name = "structure_id")
    private List<ItemStructure> itemStructures;

    @OneToMany
    @JoinColumn(name = "structure_id")
    private List<Container> containers;

    @Enumerated(EnumType.STRING)
    private StructureTipo tipo;

    @ManyToOne
    @JoinColumn(name = "ilha_id")
    private Ilha ilha;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "arena_pvp_id")
    private ArenaPvp arenaPvp;
    
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArenaPvp getArenaPvp() {
        return arenaPvp;
    }

    public void setArenaPvp(ArenaPvp arenaPvp) {
        this.arenaPvp = arenaPvp;
    }

}
