package org.cupula.model.banco;

import org.cupula.model.EntityClass;
import org.cupula.model.banco.enums.TransacaoItemTipo;
import org.cupula.model.containers.Container;
import org.cupula.model.entities.Player;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.islands.Ilha;
import org.cupula.model.items.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class TransacaoItem extends EntityClass {
    
    @ManyToOne
    private Item item;
    private String itemTransacaoResponseDTO;
    @Enumerated(EnumType.STRING)
    private TransacaoItemTipo tipo;

    @ManyToOne
    private Player jogadorOrigem;
    @ManyToOne
    private Guilda guildaOrigem;
    @ManyToOne
    private Container containerOrigem;
    @ManyToOne
    private Ilha ilhaOrigem;
    
    @ManyToOne
    private Player jogadorDestino;
    @ManyToOne
    private Guilda guildaDestino;
    @ManyToOne
    private Container containerDestino;
    @ManyToOne
    private Ilha ilhaDestino;
}
