package org.cupula.model.entities.player;

import org.cupula.model.EntityClass;
import org.cupula.model.auth.Usuario;
import org.cupula.model.containers.Container;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.PlayerTipo;
import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Player extends EntityClass {
    //Atributos Basicos visuais e de tamanho

    @ManyToOne
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private PlayerTipo tipo;
    private Long tamanhoX;
    private Long tamanhoY;
    private Long tamanhoZ;
    
    @ManyToOne
    private PlayerTipoCabelo tipoCabelo;
    private ColorMaterial corPele;

    private Long tamanhoXOrelha;
    private Long tamanhoYOrelha;
    private Long tamanhoZOrelha;
    private ColorMaterial corOrelha;

    private Long saldoBanco;
    private Long xpAtual;
    private Long nivelAtual;

    @ManyToOne
    private Container inventario;

    @ManyToOne
    private Ilha ilhaAtual;

    @OneToOne
    @JoinColumn(name = "posicao_id")
    private PlayerPosicao posicao;

    
}
