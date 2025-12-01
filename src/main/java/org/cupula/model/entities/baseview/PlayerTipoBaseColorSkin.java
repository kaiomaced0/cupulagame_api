package org.cupula.model.entities.baseview;

import org.cupula.model.entities.enums.PlayerTipo;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class PlayerTipoBaseColorSkin extends EntityClass {

    private PlayerTipo playerTipo;
    private Long possibilidade;
    
    private ColorMaterial colorMaterial;

}
