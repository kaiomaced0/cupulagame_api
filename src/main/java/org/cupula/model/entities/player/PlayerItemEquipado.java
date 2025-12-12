package org.cupula.model.entities.player;

import org.cupula.model.EntityClass;
import org.cupula.model.items.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class PlayerItemEquipado extends EntityClass {

    // Valida espaÃ§os possÃ­veis com o PlayerItemEquipadoLimite
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "item_mao_direita_id")
    private Item itemMaoDireita;
    @ManyToOne
    @JoinColumn(name = "item_mao_esquerda_id")
    private Item itemMaoEsquerda;

    @ManyToOne
    @JoinColumn(name = "item_equipado_cabeca_id")
    private Item itemEquipadoCabeca;
    @ManyToOne
    @JoinColumn(name = "item_equipado_torso_id")
    private Item itemEquipadoTorso;
    @ManyToOne
    @JoinColumn(name = "item_equipado_pernas_id")
    private Item itemEquipadoPernas;
    @ManyToOne
    @JoinColumn(name = "item_equipado_pes_id")
    private Item itemEquipadoPes;

    @ManyToOne
    @JoinColumn(name = "item_equipado_acessorio1_id")
    private Item itemEquipadoAcessorio1;

    @ManyToOne
    @JoinColumn(name = "item_equipado_acessorio2_id")
    private Item itemEquipadoAcessorio2;

    @ManyToOne
    @JoinColumn(name = "item_equipado_acessorio3_id")
    private Item itemEquipadoAcessorio3;

    @ManyToOne
    @JoinColumn(name = "item_equipado_acessorio4_id")
    private Item itemEquipadoAcessorio4;
    
    // Getters e Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Item getItemMaoDireita() {
        return itemMaoDireita;
    }

    public void setItemMaoDireita(Item itemMaoDireita) {
        this.itemMaoDireita = itemMaoDireita;
    }

    public Item getItemMaoEsquerda() {
        return itemMaoEsquerda;
    }

    public void setItemMaoEsquerda(Item itemMaoEsquerda) {
        this.itemMaoEsquerda = itemMaoEsquerda;
    }

    public Item getItemEquipadoCabeca() {
        return itemEquipadoCabeca;
    }

    public void setItemEquipadoCabeca(Item itemEquipadoCabeca) {
        this.itemEquipadoCabeca = itemEquipadoCabeca;
    }

    public Item getItemEquipadoTorso() {
        return itemEquipadoTorso;
    }

    public void setItemEquipadoTorso(Item itemEquipadoTorso) {
        this.itemEquipadoTorso = itemEquipadoTorso;
    }

    public Item getItemEquipadoPernas() {
        return itemEquipadoPernas;
    }

    public void setItemEquipadoPernas(Item itemEquipadoPernas) {
        this.itemEquipadoPernas = itemEquipadoPernas;
    }

    public Item getItemEquipadoPes() {
        return itemEquipadoPes;
    }

    public void setItemEquipadoPes(Item itemEquipadoPes) {
        this.itemEquipadoPes = itemEquipadoPes;
    }

    public Item getItemEquipadoAcessorio1() {
        return itemEquipadoAcessorio1;
    }

    public void setItemEquipadoAcessorio1(Item itemEquipadoAcessorio1) {
        this.itemEquipadoAcessorio1 = itemEquipadoAcessorio1;
    }

    public Item getItemEquipadoAcessorio2() {
        return itemEquipadoAcessorio2;
    }

    public void setItemEquipadoAcessorio2(Item itemEquipadoAcessorio2) {
        this.itemEquipadoAcessorio2 = itemEquipadoAcessorio2;
    }

    public Item getItemEquipadoAcessorio3() {
        return itemEquipadoAcessorio3;
    }

    public void setItemEquipadoAcessorio3(Item itemEquipadoAcessorio3) {
        this.itemEquipadoAcessorio3 = itemEquipadoAcessorio3;
    }

    public Item getItemEquipadoAcessorio4() {
        return itemEquipadoAcessorio4;
    }

    public void setItemEquipadoAcessorio4(Item itemEquipadoAcessorio4) {
        this.itemEquipadoAcessorio4 = itemEquipadoAcessorio4;
    }
}
