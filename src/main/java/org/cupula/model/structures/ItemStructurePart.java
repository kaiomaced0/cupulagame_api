package org.cupula.model.structures;

import org.cupula.model.EntityClass;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "item_structure_part")
public class ItemStructurePart extends EntityClass {
    
    private Long inicioX;
    private Long inicioY;
    private Long fimX;
    private Long fimY;
    private Long inicioZ;
    private Long fimZ;
    
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
    @ManyToOne
    @JoinColumn(name = "color_material_id")
    private ColorMaterial color;

    private Boolean areaContato;
    public Long getInicioX() {
        return inicioX;
    }
    public void setInicioX(Long inicioX) {
        this.inicioX = inicioX;
    }
    public Long getInicioY() {
        return inicioY;
    }
    public void setInicioY(Long inicioY) {
        this.inicioY = inicioY;
    }
    public Long getFimX() {
        return fimX;
    }
    public void setFimX(Long fimX) {
        this.fimX = fimX;
    }
    public Long getFimY() {
        return fimY;
    }
    public void setFimY(Long fimY) {
        this.fimY = fimY;
    }
    public Long getInicioZ() {
        return inicioZ;
    }
    public void setInicioZ(Long inicioZ) {
        this.inicioZ = inicioZ;
    }
    public Long getFimZ() {
        return fimZ;
    }
    public void setFimZ(Long fimZ) {
        this.fimZ = fimZ;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public ColorMaterial getColor() {
        return color;
    }
    public void setColor(ColorMaterial color) {
        this.color = color;
    }
    public Boolean getAreaContato() {
        return areaContato;
    }
    public void setAreaContato(Boolean areaContato) {
        this.areaContato = areaContato;
    }

}
