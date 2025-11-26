package org.cupula.model.entities;

import org.cupula.model.containers.Container;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.PlayerTipo;
import org.cupula.model.items.Item;
import org.cupula.model.structures.view.ColorMaterial;

public class Player {
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

    private Item itemMaoDireita;
    private Item itemMaoEsquerda;

    private Item itemEquipadoCabeca;
    private Item itemEquipadoTorso;
    private Item itemEquipadoPernas;
    private Item itemEquipadoPes;

    private Item itemEquipadoAcessorio1;
    private Item itemEquipadoAcessorio2;
    private Item itemEquipadoAcessorio3;
    private Item itemEquipadoAcessorio4;

    


}
