package org.cupula.model.entities.baseview;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;

import jakarta.persistence.Entity;

@Entity
public class PlayerTipoBaseTamanho extends EntityClass {

    private PlayerRaca playerRaca;
    private Long possibilidade;
    
    private Long eixoXMinimo;
    private Long eixoXMaximo;
    private Long eixoYMinimo;
    private Long eixoYMaximo;
    private Long eixoZMinimo;
    private Long eixoZMaximo;


}
