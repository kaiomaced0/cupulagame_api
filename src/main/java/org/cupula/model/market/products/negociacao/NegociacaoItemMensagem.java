package org.cupula.model.market.products.negociacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;

@Entity
public class NegociacaoItemMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    private Player comprador;
    private Player vendedor;
    public String getMensagemCriptografada() {
        return mensagemCriptografada;
    }
    public void setMensagemCriptografada(String mensagemCriptografada) {
        this.mensagemCriptografada = mensagemCriptografada;
    }
    public Player getComprador() {
        return comprador;
    }
    public void setComprador(Player comprador) {
        this.comprador = comprador;
    }
    public Player getVendedor() {
        return vendedor;
    }
    public void setVendedor(Player vendedor) {
        this.vendedor = vendedor;
    }

}
