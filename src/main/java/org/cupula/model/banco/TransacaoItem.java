package org.cupula.model.banco;

import org.cupula.model.EntityClass;
import org.cupula.model.banco.enums.TransacaoItemTipo;
import org.cupula.model.containers.Container;
import org.cupula.model.entities.player.Player;
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
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public String getItemTransacaoResponseDTO() {
        return itemTransacaoResponseDTO;
    }
    public void setItemTransacaoResponseDTO(String itemTransacaoResponseDTO) {
        this.itemTransacaoResponseDTO = itemTransacaoResponseDTO;
    }
    public TransacaoItemTipo getTipo() {
        return tipo;
    }
    public void setTipo(TransacaoItemTipo tipo) {
        this.tipo = tipo;
    }
    public Player getJogadorOrigem() {
        return jogadorOrigem;
    }
    public void setJogadorOrigem(Player jogadorOrigem) {
        this.jogadorOrigem = jogadorOrigem;
    }
    public Guilda getGuildaOrigem() {
        return guildaOrigem;
    }
    public void setGuildaOrigem(Guilda guildaOrigem) {
        this.guildaOrigem = guildaOrigem;
    }
    public Container getContainerOrigem() {
        return containerOrigem;
    }
    public void setContainerOrigem(Container containerOrigem) {
        this.containerOrigem = containerOrigem;
    }
    public Ilha getIlhaOrigem() {
        return ilhaOrigem;
    }
    public void setIlhaOrigem(Ilha ilhaOrigem) {
        this.ilhaOrigem = ilhaOrigem;
    }
    public Player getJogadorDestino() {
        return jogadorDestino;
    }
    public void setJogadorDestino(Player jogadorDestino) {
        this.jogadorDestino = jogadorDestino;
    }
    public Guilda getGuildaDestino() {
        return guildaDestino;
    }
    public void setGuildaDestino(Guilda guildaDestino) {
        this.guildaDestino = guildaDestino;
    }
    public Container getContainerDestino() {
        return containerDestino;
    }
    public void setContainerDestino(Container containerDestino) {
        this.containerDestino = containerDestino;
    }
    public Ilha getIlhaDestino() {
        return ilhaDestino;
    }
    public void setIlhaDestino(Ilha ilhaDestino) {
        this.ilhaDestino = ilhaDestino;
    }

}
