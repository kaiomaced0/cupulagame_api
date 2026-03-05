package org.cupula.model.containers;

import org.cupula.model.EntityClass;
import org.cupula.model.containers.enums.ContainerTipo;
import org.cupula.model.entities.mob.Mob;
import org.cupula.model.structures.StructureUnit;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "container")
public class Container extends EntityClass {
    
    @Enumerated(EnumType.STRING)
    private ContainerTipo containerTipo;

    private Long capacidadeMaximaEspaco;
    private Long pesoMaximo;
    
    // Posição 3D no mundo (para containers soltos no mapa)
    private Long posicaoX;
    private Long posicaoY;
    private Long posicaoZ;
    
    // Visual do container (baú de madeira, baú de ferro, mochila, etc)
    @ManyToOne
    @JoinColumn(name = "structure_unit_id")
    private StructureUnit structureUnit;
    
    // Container vinculado a um mob (mob com inventário)
    @ManyToOne
    @JoinColumn(name = "mob_id")
    private Mob mob;
    
    // Container dentro de uma estrutura (baú dentro de casa, loja, empresa, etc)
    @ManyToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;
    
    public ContainerTipo getContainerTipo() {
        return containerTipo;
    }
    
    public void setContainerTipo(ContainerTipo containerTipo) {
        this.containerTipo = containerTipo;
    }
    
    public Long getCapacidadeMaximaEspaco() {
        return capacidadeMaximaEspaco;
    }
    
    public void setCapacidadeMaximaEspaco(Long capacidadeMaximaEspaco) {
        this.capacidadeMaximaEspaco = capacidadeMaximaEspaco;
    }
    
    public Long getPesoMaximo() {
        return pesoMaximo;
    }
    
    public void setPesoMaximo(Long pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public Long getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(Long posicaoX) {
        this.posicaoX = posicaoX;
    }

    public Long getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(Long posicaoY) {
        this.posicaoY = posicaoY;
    }

    public Long getPosicaoZ() {
        return posicaoZ;
    }

    public void setPosicaoZ(Long posicaoZ) {
        this.posicaoZ = posicaoZ;
    }

    public StructureUnit getStructureUnit() {
        return structureUnit;
    }

    public void setPosicao(Long x, Long y, Long z) {
        this.posicaoX = x;
        this.posicaoY = y;
        this.posicaoZ = z;
    }

    public void setStructureUnit(StructureUnit structureUnit) {
        this.structureUnit = structureUnit;
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

}
