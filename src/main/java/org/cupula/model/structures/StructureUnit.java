package org.cupula.model.structures;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.auth.Usuario;
import org.cupula.model.structures.enums.StructureUnitTipo;
import org.cupula.model.structures.enums.Layer;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "structure_unit")
public class StructureUnit extends EntityClass {

    private String nome;

    @OneToMany
    @JoinColumn(name = "structure_unit_id")
    private List<StructureUnitPart> parts;

    @Enumerated(EnumType.STRING)
    private Layer layer;
    
    @Enumerated(EnumType.STRING)
    private StructureUnitTipo tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<StructureUnitPart> getParts() {
        return parts;
    }
    public void setParts(List<StructureUnitPart> parts) {
        this.parts = parts;
    }
    public Layer getLayer() {
        return layer;
    }
    public void setLayer(Layer layer) {
        this.layer = layer;
    }
    public StructureUnitTipo getTipo() {
        return tipo;
    }
    public void setTipo(StructureUnitTipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
