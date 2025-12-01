package org.cupula.model.entities.baseview.orelha;

import java.util.List;

import org.cupula.model.entities.enums.PlayerTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class PlayerTipoBaseOrelha extends EntityClass {
    
    private PlayerTipo playerTipo;
    private Long possibilidade;
    
    private Long eixoXMinimo;
    private Long eixoXMaximo;
    private Long eixoYMinimo;
    private Long eixoYMaximo;
    private Long eixoZMinimo;
    private Long eixoZMaximo;

    private List<TipoOrelhaColorMaterial> possiveisColorMaterials;
    
}
