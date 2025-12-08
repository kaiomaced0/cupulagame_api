package org.cupula.model.entities.player;

import java.time.LocalDateTime;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.mob.Mob;
import org.cupula.model.entities.player.enums.TipoMorte;
import org.cupula.model.islands.Ilha;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayerMorte extends EntityClass {

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Enumerated(EnumType.STRING)
    private TipoMorte tipoMorte;

    // Coordenadas da morte
    private Long posicaoMorteX;
    private Long posicaoMorteY;
    private Long posicaoMorteZ;

    @ManyToOne
    @JoinColumn(name = "ilha_morte_id")
    private Ilha ilhaMorte;

    // Quem/O que matou
    @ManyToOne
    @JoinColumn(name = "assassino_player_id")
    private Player assassinoPlayer; // Caso seja PVP

    @ManyToOne
    @JoinColumn(name = "assassino_mob_id")
    private Mob assassinoMob; // Caso seja PVE

    // Penalidades aplicadas
    private Long xpPerdido;
    private Long goldPerdido;
    private Boolean perdeuItens = false;

    // Ponto de respawn
    private Long posicaoRespawnX;
    private Long posicaoRespawnY;
    private Long posicaoRespawnZ;

    @ManyToOne
    @JoinColumn(name = "ilha_respawn_id")
    private Ilha ilhaRespawn;

    private LocalDateTime dataHoraMorte;
    private LocalDateTime dataHoraRespawn;

    // Sistema de Vingança
    private Boolean vindicado = false;

    // Getters e Setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TipoMorte getTipoMorte() {
        return tipoMorte;
    }

    public void setTipoMorte(TipoMorte tipoMorte) {
        this.tipoMorte = tipoMorte;
    }

    public Long getPosicaoMorteX() {
        return posicaoMorteX;
    }

    public void setPosicaoMorteX(Long posicaoMorteX) {
        this.posicaoMorteX = posicaoMorteX;
    }

    public Long getPosicaoMorteY() {
        return posicaoMorteY;
    }

    public void setPosicaoMorteY(Long posicaoMorteY) {
        this.posicaoMorteY = posicaoMorteY;
    }

    public Long getPosicaoMorteZ() {
        return posicaoMorteZ;
    }

    public void setPosicaoMorteZ(Long posicaoMorteZ) {
        this.posicaoMorteZ = posicaoMorteZ;
    }

    public Ilha getIlhaMorte() {
        return ilhaMorte;
    }

    public void setIlhaMorte(Ilha ilhaMorte) {
        this.ilhaMorte = ilhaMorte;
    }

    public Player getAssassinoPlayer() {
        return assassinoPlayer;
    }

    public void setAssassinoPlayer(Player assassinoPlayer) {
        this.assassinoPlayer = assassinoPlayer;
    }

    public Mob getAssassinoMob() {
        return assassinoMob;
    }

    public void setAssassinoMob(Mob assassinoMob) {
        this.assassinoMob = assassinoMob;
    }

    public Long getXpPerdido() {
        return xpPerdido;
    }

    public void setXpPerdido(Long xpPerdido) {
        this.xpPerdido = xpPerdido;
    }

    public Long getGoldPerdido() {
        return goldPerdido;
    }

    public void setGoldPerdido(Long goldPerdido) {
        this.goldPerdido = goldPerdido;
    }

    public Boolean getPerdeuItens() {
        return perdeuItens;
    }

    public void setPerdeuItens(Boolean perdeuItens) {
        this.perdeuItens = perdeuItens;
    }

    public Long getPosicaoRespawnX() {
        return posicaoRespawnX;
    }

    public void setPosicaoRespawnX(Long posicaoRespawnX) {
        this.posicaoRespawnX = posicaoRespawnX;
    }

    public Long getPosicaoRespawnY() {
        return posicaoRespawnY;
    }

    public void setPosicaoRespawnY(Long posicaoRespawnY) {
        this.posicaoRespawnY = posicaoRespawnY;
    }

    public Long getPosicaoRespawnZ() {
        return posicaoRespawnZ;
    }

    public void setPosicaoRespawnZ(Long posicaoRespawnZ) {
        this.posicaoRespawnZ = posicaoRespawnZ;
    }

    public Ilha getIlhaRespawn() {
        return ilhaRespawn;
    }

    public void setIlhaRespawn(Ilha ilhaRespawn) {
        this.ilhaRespawn = ilhaRespawn;
    }

    public LocalDateTime getDataHoraMorte() {
        return dataHoraMorte;
    }

    public void setDataHoraMorte(LocalDateTime dataHoraMorte) {
        this.dataHoraMorte = dataHoraMorte;
    }

    public LocalDateTime getDataHoraRespawn() {
        return dataHoraRespawn;
    }

    public void setDataHoraRespawn(LocalDateTime dataHoraRespawn) {
        this.dataHoraRespawn = dataHoraRespawn;
    }

    public Boolean getVindicado() {
        return vindicado;
    }

    public void setVindicado(Boolean vindicado) {
        this.vindicado = vindicado;
    }
}
