package org.cupula.model.entities.player;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class PlayerItemEquipadoLimite extends EntityClass {
    
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Boolean itemMaoDireita;
    private Boolean itemMaoEsquerda;

    private Boolean itemEquipadoCabeca;
    private Boolean itemEquipadoTorso;
    private Boolean itemEquipadoPernas;
    private Boolean itemEquipadoPes;

    private Boolean itemEquipadoAcessorio1;
    private Boolean itemEquipadoAcessorio2;
    private Boolean itemEquipadoAcessorio3;
    private Boolean itemEquipadoAcessorio4;
    private Boolean itemEquipadoAcessorio5;
    private Boolean itemEquipadoAcessorio6;
    
    // Getters e Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Boolean getItemMaoDireita() {
        return itemMaoDireita;
    }

    public void setItemMaoDireita(Boolean itemMaoDireita) {
        this.itemMaoDireita = itemMaoDireita;
    }

    public Boolean getItemMaoEsquerda() {
        return itemMaoEsquerda;
    }

    public void setItemMaoEsquerda(Boolean itemMaoEsquerda) {
        this.itemMaoEsquerda = itemMaoEsquerda;
    }

    public Boolean getItemEquipadoCabeca() {
        return itemEquipadoCabeca;
    }

    public void setItemEquipadoCabeca(Boolean itemEquipadoCabeca) {
        this.itemEquipadoCabeca = itemEquipadoCabeca;
    }

    public Boolean getItemEquipadoTorso() {
        return itemEquipadoTorso;
    }

    public void setItemEquipadoTorso(Boolean itemEquipadoTorso) {
        this.itemEquipadoTorso = itemEquipadoTorso;
    }

    public Boolean getItemEquipadoPernas() {
        return itemEquipadoPernas;
    }

    public void setItemEquipadoPernas(Boolean itemEquipadoPernas) {
        this.itemEquipadoPernas = itemEquipadoPernas;
    }

    public Boolean getItemEquipadoPes() {
        return itemEquipadoPes;
    }

    public void setItemEquipadoPes(Boolean itemEquipadoPes) {
        this.itemEquipadoPes = itemEquipadoPes;
    }

    public Boolean getItemEquipadoAcessorio1() {
        return itemEquipadoAcessorio1;
    }

    public void setItemEquipadoAcessorio1(Boolean itemEquipadoAcessorio1) {
        this.itemEquipadoAcessorio1 = itemEquipadoAcessorio1;
    }

    public Boolean getItemEquipadoAcessorio2() {
        return itemEquipadoAcessorio2;
    }

    public void setItemEquipadoAcessorio2(Boolean itemEquipadoAcessorio2) {
        this.itemEquipadoAcessorio2 = itemEquipadoAcessorio2;
    }

    public Boolean getItemEquipadoAcessorio3() {
        return itemEquipadoAcessorio3;
    }

    public void setItemEquipadoAcessorio3(Boolean itemEquipadoAcessorio3) {
        this.itemEquipadoAcessorio3 = itemEquipadoAcessorio3;
    }

    public Boolean getItemEquipadoAcessorio4() {
        return itemEquipadoAcessorio4;
    }

    public void setItemEquipadoAcessorio4(Boolean itemEquipadoAcessorio4) {
        this.itemEquipadoAcessorio4 = itemEquipadoAcessorio4;
    }

    public Boolean getItemEquipadoAcessorio5() {
        return itemEquipadoAcessorio5;
    }

    public void setItemEquipadoAcessorio5(Boolean itemEquipadoAcessorio5) {
        this.itemEquipadoAcessorio5 = itemEquipadoAcessorio5;
    }

    public Boolean getItemEquipadoAcessorio6() {
        return itemEquipadoAcessorio6;
    }

    public void setItemEquipadoAcessorio6(Boolean itemEquipadoAcessorio6) {
        this.itemEquipadoAcessorio6 = itemEquipadoAcessorio6;
    }
}
