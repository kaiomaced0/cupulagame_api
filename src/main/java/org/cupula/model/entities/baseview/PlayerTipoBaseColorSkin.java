package org.cupula.model.entities.baseview;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "player_tipo_base_color_skin")
public class PlayerTipoBaseColorSkin extends EntityClass {

    @Enumerated(EnumType.STRING)
    private PlayerRaca playerRaca;
    private Long possibilidade;
    
    @ManyToOne
    @JoinColumn(name = "color_material_id")
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
