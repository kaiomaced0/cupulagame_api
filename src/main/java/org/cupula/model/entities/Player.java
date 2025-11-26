package org.cupula.model.entities;

import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.PlayerTipo;
import org.cupula.model.structures.view.ColorMaterial;

public class Player {
    
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
}
