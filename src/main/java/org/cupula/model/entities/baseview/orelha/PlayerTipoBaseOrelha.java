package org.cupula.model.entities.baseview.orelha;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;

import jakarta.persistence.Entity;

@Entity
public class PlayerTipoBaseOrelha extends EntityClass {
    
    private PlayerRaca playerRaca;
    private Long possibilidade;
    
    private Long eixoXMinimo;
    private Long eixoXMaximo;
    private Long eixoYMinimo;
    private Long eixoYMaximo;
    private Long eixoZMinimo;
    private Long eixoZMaximo;

    private List<TipoOrelhaColorMaterial> possiveisColorMaterials;
    
}
