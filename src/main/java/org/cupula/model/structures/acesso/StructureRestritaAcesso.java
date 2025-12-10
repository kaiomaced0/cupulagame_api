package org.cupula.model.structures.acesso;

import java.util.List;

import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class StructureRestritaAcesso extends EntityClass {
    
    private Structure structure;
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
