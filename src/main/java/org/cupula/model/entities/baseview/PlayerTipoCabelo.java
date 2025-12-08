package org.cupula.model.entities.baseview;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;

@Entity
public class PlayerTipoCabelo extends EntityClass {
    private String nome;
    private String path;
    private Long possibilidade;
    private PlayerRaca playerRaca;
    private Structure estrutura;
}
