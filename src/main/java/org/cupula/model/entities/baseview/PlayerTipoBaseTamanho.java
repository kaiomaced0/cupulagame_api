package org.cupula.model.entities.baseview;

import org.cupula.model.entities.enums.PlayerTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class PlayerTipoBaseTamanho extends EntityClass {

    private PlayerTipo playerTipo;
    private Long possibilidade;
    
    private Long eixoXMinimo;
    private Long eixoXMaximo;
    private Long eixoYMinimo;
    private Long eixoYMaximo;
    private Long eixoZMinimo;
    private Long eixoZMaximo;


}
