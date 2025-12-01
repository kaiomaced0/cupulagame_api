package org.cupula.model.market.products.negociacao;

import org.cupula.model.entities.Player;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class NegociacaoItemMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    private Player comprador;
    private Player vendedor;
}
