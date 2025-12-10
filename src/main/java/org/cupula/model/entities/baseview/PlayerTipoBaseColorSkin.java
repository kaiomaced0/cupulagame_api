package org.cupula.model.entities.baseview;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;

@Entity
public class PlayerTipoBaseColorSkin extends EntityClass {

    private PlayerRaca playerRaca;
    private Long possibilidade;
    
    private ColorMaterial colorMaterial;

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
    public ColorMaterial getColorMaterial() {
        return colorMaterial;
    }
    public void setColorMaterial(ColorMaterial colorMaterial) {
        this.colorMaterial = colorMaterial;
    }

}
