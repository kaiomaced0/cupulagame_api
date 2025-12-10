    package org.cupula.model.pvp;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.mob.Mob;
import org.cupula.model.entities.player.Player;
import org.cupula.model.pvp.enums.PvpMovimentoTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class PvpMovimento extends EntityClass {
    
    @Enumerated(EnumType.STRING)
    private PvpMovimentoTipo tipo;

    private Long xInicial;
    private Long xFinal;

    private Long yInicial;
    private Long yFinal;

    private Long zInicial;
    private Long zFinal;
    
    @ManyToOne
    private PvpTurno pvpTurno;
    
    private Integer ordem;
    
    @ManyToOne
    private Pvp pvp;

    @ManyToOne
    private Player player;
    
    @ManyToOne
    private Mob mob;

    private Long danoCausado;
    private Long danoRecebido;
    private Boolean sucesso;
    
    @ManyToOne
    private PvpMovimento movimentoInimigo;
    
    // Getters e Setters
    public PvpMovimentoTipo getTipo() {
        return tipo;
    }

    public void setTipo(PvpMovimentoTipo tipo) {
        this.tipo = tipo;
    }

    public Long getxInicial() {
        return xInicial;
    }

    public void setxInicial(Long xInicial) {
        this.xInicial = xInicial;
    }

    public Long getxFinal() {
        return xFinal;
    }

    public void setxFinal(Long xFinal) {
        this.xFinal = xFinal;
    }

    public Long getyInicial() {
        return yInicial;
    }

    public void setyInicial(Long yInicial) {
        this.yInicial = yInicial;
    }

    public Long getyFinal() {
        return yFinal;
    }

    public void setyFinal(Long yFinal) {
        this.yFinal = yFinal;
    }

    public Long getzInicial() {
        return zInicial;
    }

    public void setzInicial(Long zInicial) {
        this.zInicial = zInicial;
    }

    public Long getzFinal() {
        return zFinal;
    }

    public void setzFinal(Long zFinal) {
        this.zFinal = zFinal;
    }

    public PvpTurno getPvpTurno() {
        return pvpTurno;
    }

    public void setPvpTurno(PvpTurno pvpTurno) {
        this.pvpTurno = pvpTurno;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Pvp getPvp() {
        return pvp;
    }

    public void setPvp(Pvp pvp) {
        this.pvp = pvp;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }

    public Long getDanoCausado() {
        return danoCausado;
    }

    public void setDanoCausado(Long danoCausado) {
        this.danoCausado = danoCausado;
    }

    public Long getDanoRecebido() {
        return danoRecebido;
    }

    public void setDanoRecebido(Long danoRecebido) {
        this.danoRecebido = danoRecebido;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public PvpMovimento getMovimentoInimigo() {
        return movimentoInimigo;
    }

    public void setMovimentoInimigo(PvpMovimento movimentoInimigo) {
        this.movimentoInimigo = movimentoInimigo;
    }
    public Long getXInicial() {
        return xInicial;
    }
    public void setXInicial(Long xInicial) {
        this.xInicial = xInicial;
    }
    public Long getXFinal() {
        return xFinal;
    }
    public void setXFinal(Long xFinal) {
        this.xFinal = xFinal;
    }
    public Long getYInicial() {
        return yInicial;
    }
    public void setYInicial(Long yInicial) {
        this.yInicial = yInicial;
    }
    public Long getYFinal() {
        return yFinal;
    }
    public void setYFinal(Long yFinal) {
        this.yFinal = yFinal;
    }
    public Long getZInicial() {
        return zInicial;
    }
    public void setZInicial(Long zInicial) {
        this.zInicial = zInicial;
    }
    public Long getZFinal() {
        return zFinal;
    }
    public void setZFinal(Long zFinal) {
        this.zFinal = zFinal;
    }

}
