package org.cupula.model.islands.cargos;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;
import org.cupula.model.islands.Ilha;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaMembro extends EntityClass {
    
    @ManyToOne
    @JoinColumn(name = "ilha_id")
    private Ilha ilha;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Long dataEntrada;
    private Long dataUltimaAtividade;
    private Boolean isOnline;

    private Long minutosDisponiveisParaIlhaSemanalmente;

    private String apelido;
    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
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
    public Long getMinutosDisponiveisParaIlhaSemanalmente() {
        return minutosDisponiveisParaIlhaSemanalmente;
    }
    public void setMinutosDisponiveisParaIlhaSemanalmente(Long minutosDisponiveisParaIlhaSemanalmente) {
        this.minutosDisponiveisParaIlhaSemanalmente = minutosDisponiveisParaIlhaSemanalmente;
    }
    public String getApelido() {
        return apelido;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

}
