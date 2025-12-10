package org.cupula.model.entities.baseview;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;

import jakarta.persistence.Entity;

@Entity
public class PlayerTipoBaseTamanho extends EntityClass {

    private PlayerRaca playerRaca;
    private Long possibilidade;
    
    private Long eixoXMinimo;
    private Long eixoXMaximo;
    private Long eixoYMinimo;
    private Long eixoYMaximo;
    private Long eixoZMinimo;
    private Long eixoZMaximo;


    public PlayerRaca getPlayerRaca() {
        return playerRaca;
    }
    public void setPlayerRaca(PlayerRaca playerRaca) {
        this.playerRaca = playerRaca;
    }
    public Long getPossibilidade() {
        return possibilidade;
    }
    public void setPossibilidade(Long possibilidade) {
        this.possibilidade = possibilidade;
    }
    public Long getEixoXMinimo() {
        return eixoXMinimo;
    }
    public void setEixoXMinimo(Long eixoXMinimo) {
        this.eixoXMinimo = eixoXMinimo;
    }
    public Long getEixoXMaximo() {
        return eixoXMaximo;
    }
    public void setEixoXMaximo(Long eixoXMaximo) {
        this.eixoXMaximo = eixoXMaximo;
    }
    public Long getEixoYMinimo() {
        return eixoYMinimo;
    }
    public void setEixoYMinimo(Long eixoYMinimo) {
        this.eixoYMinimo = eixoYMinimo;
    }
    public Long getEixoYMaximo() {
        return eixoYMaximo;
    }
    public void setEixoYMaximo(Long eixoYMaximo) {
        this.eixoYMaximo = eixoYMaximo;
    }
    public Long getEixoZMinimo() {
        return eixoZMinimo;
    }
    public void setEixoZMinimo(Long eixoZMinimo) {
        this.eixoZMinimo = eixoZMinimo;
    }
    public Long getEixoZMaximo() {
        return eixoZMaximo;
    }
    public void setEixoZMaximo(Long eixoZMaximo) {
        this.eixoZMaximo = eixoZMaximo;
    }

}
