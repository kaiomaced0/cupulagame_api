package org.cupula.model.market.products.negociacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "negociacao_item_mensagem")
public class NegociacaoItemMensagem extends EntityClass {
    
    private String mensagemCriptografada;
    
    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Player comprador;
    
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
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
