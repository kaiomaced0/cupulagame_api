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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "transacao_item")
public class TransacaoItem extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String itemTransacaoResponseDTO;
    @Enumerated(EnumType.STRING)
    private TransacaoItemTipo tipo;

    @ManyToOne
    @JoinColumn(name = "jogador_origem_id")
    private Player jogadorOrigem;

    @ManyToOne
    @JoinColumn(name = "guilda_origem_id")
    private Guilda guildaOrigem;
    
    @ManyToOne
    @JoinColumn(name = "container_origem_id")
    private Container containerOrigem;

    @ManyToOne
    @JoinColumn(name = "ilha_origem_id")
    private Ilha ilhaOrigem;
    
    @ManyToOne
    @JoinColumn(name = "jogador_destino_id")
    private Player jogadorDestino;

    @ManyToOne
    @JoinColumn(name = "guilda_destino_id")
    private Guilda guildaDestino;

    @ManyToOne
    @JoinColumn(name = "container_destino_id")
    private Container containerDestino;

    @ManyToOne
    @JoinColumn(name = "ilha_destino_id")
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
