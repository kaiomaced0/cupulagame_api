package org.cupula.model.market.products.negociacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;

@Entity
public class NegociacaoItemMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    private Player comprador;
    private Player vendedor;
}
