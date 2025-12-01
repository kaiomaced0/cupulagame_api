package org.cupula.model.banco;

import org.cupula.model.banco.enums.TransacaoItemTipo;
import org.cupula.model.containers.Container;
import org.cupula.model.entities.Player;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.islands.Ilha;
import org.cupula.model.items.Item;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class TransacaoItem extends EntityClass {
    
    private Item item;
    private String itemTransacaoResponseDTO;
    private TransacaoItemTipo tipo;

    private Player jogadorOrigem;
    private Guilda guildaOrigem;
    private Container containerOrigem;
    private Ilha ilhaOrigem;
    
    private Player jogadorDestino;
    private Guilda guildaDestino;
    private Container containerDestino;
    private Ilha ilhaDestino;
}
