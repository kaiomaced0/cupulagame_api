package org.cupula.model.entities;

import org.cupula.model.EntityClass;
import org.cupula.model.containers.Container;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.PlayerTipo;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;

@Entity
public class Player extends EntityClass {
    //Atributos Basicos visuais e de tamanho
    private PlayerTipo tipo;
    private Long tamanhoX;
    private Long tamanhoY;
    private Long tamanhoZ;
    
    private PlayerTipoCabelo tipoCabelo;
    private ColorMaterial corPele;

    private Long tamanhoXOrelha;
    private Long tamanhoYOrelha;
    private Long tamanhoZOrelha;
    private ColorMaterial corOrelha;


    private Long saldoBanco;
    private Long xpAtual;
    private Long nivelAtual;

    private Container inventario;

}
