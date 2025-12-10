package org.cupula.model.guilda;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;
import org.cupula.model.guilda.enums.CargoGuilda;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GuildaMembro extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player membro;
    
    private String apelido;

    @Enumerated(EnumType.STRING)
    private CargoGuilda cargo;

    private Long dataEntrada;
    private Long dataUltimaAtividade;
    private Boolean isOnline;

    private Long minutosDisponiveisParaGuildaSemanalmente;


    public Guilda getGuilda() {
        return guilda;
    }

    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }

    public Player getMembro() {
        return membro;
    }

    public void setMembro(Player membro) {
        this.membro = membro;
    }

    public CargoGuilda getCargo() {
        return cargo;
    }

    public void setCargo(CargoGuilda cargo) {
        this.cargo = cargo;
    }

    public Long getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Long dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Long getDataUltimaAtividade() {
        return dataUltimaAtividade;
    }

    public void setDataUltimaAtividade(Long dataUltimaAtividade) {
        this.dataUltimaAtividade = dataUltimaAtividade;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Long getMinutosDisponiveisParaGuildaSemanalmente() {
        return minutosDisponiveisParaGuildaSemanalmente;
    }

    public void setMinutosDisponiveisParaGuildaSemanalmente(Long minutosDisponiveisParaGuildaSemanalmente) {
        this.minutosDisponiveisParaGuildaSemanalmente = minutosDisponiveisParaGuildaSemanalmente;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    
}
