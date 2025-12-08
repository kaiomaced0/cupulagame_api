package org.cupula.model.entities.baseview;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;

@Entity
public class PlayerTipoBaseColorSkin extends EntityClass {

    private PlayerRaca playerRaca;
    private Long possibilidade;
    
    private ColorMaterial colorMaterial;

}
