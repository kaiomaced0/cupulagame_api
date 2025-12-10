package org.cupula.model.structures.acesso;

import java.util.List;

import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import org.cupula.model.EntityClass;

import jakarta.persistence.OneToOne;

@Entity
public class StructureRestritaAcesso extends EntityClass {
    // caso a Structure tiver restricao, é criado um structureRestitaAcesso vinculado a ela
    @OneToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @OneToMany
    @JoinColumn(name = "structure_restrita_acesso_id")
    private List<StructureAcesso> acessos;
    public Structure getStructure() {
        return structure;
    }
    public void setStructure(Structure structure) {
        this.structure = structure;
    }
    public List<StructureAcesso> getAcessos() {
        return acessos;
    }
    public void setAcessos(List<StructureAcesso> acessos) {
        this.acessos = acessos;
    }

}
