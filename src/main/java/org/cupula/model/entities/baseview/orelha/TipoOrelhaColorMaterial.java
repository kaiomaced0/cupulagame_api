package org.cupula.model.entities.baseview.orelha;

import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class TipoOrelhaColorMaterial extends EntityClass {
    private ColorMaterial colorMaterial;
    private Long possibilidade;
    
}
