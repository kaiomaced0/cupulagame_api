package org.cupula.model.entities.baseview.orelha;

import org.cupula.model.EntityClass;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;

@Entity
public class TipoOrelhaColorMaterial extends EntityClass {
    private ColorMaterial colorMaterial;
    private Long possibilidade;
    
}
