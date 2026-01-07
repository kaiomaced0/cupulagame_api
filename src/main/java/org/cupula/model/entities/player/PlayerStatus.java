package org.cupula.model.entities.player;

import java.time.LocalDateTime;

import org.cupula.model.EntityClass;
import org.cupula.model.containers.Container;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "player_status")
public class PlayerStatus extends EntityClass {

    // Progressão
    private Long xpAtual = 0L;
    private Long nivelAtual = 1L;

    // Atributos Base de Combate
    private Long hpMaximo = 100L;
    private Long hpAtual = 100L;
    private Long estaminaMaxima = 100L;
    private Long estaminaAtual = 100L;

    // Atributos de Movimento
    private Long velocidadePadrao = 50L;

    // Economia
    private Long saldoBanco = 0L;

    // Estatísticas de Combate
    private Long totalMortes = 0L;
    private Long totalKillsMobs = 0L;
    private Long totalKillsPlayers = 0L;
    private LocalDateTime ultimaMorte;

    // Inventário
    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Container inventario;

    @OneToOne
    @JoinColumn(name = "posicao_id")
    private PlayerPosicao posicao;

    // Getters e Setters
    public Long getXpAtual() {
        return xpAtual;
    }

    public void setXpAtual(Long xpAtual) {
        this.xpAtual = xpAtual;
    }

    public Long getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(Long nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public Long getHpMaximo() {
        return hpMaximo;
    }

    public void setHpMaximo(Long hpMaximo) {
        this.hpMaximo = hpMaximo;
    }

    public Long getHpAtual() {
        return hpAtual;
    }

    public void setHpAtual(Long hpAtual) {
        this.hpAtual = hpAtual;
    }

    public Long getEstaminaMaxima() {
        return estaminaMaxima;
    }

    public void setEstaminaMaxima(Long estaminaMaxima) {
        this.estaminaMaxima = estaminaMaxima;
    }

    public Long getEstaminaAtual() {
        return estaminaAtual;
    }

    public void setEstaminaAtual(Long estaminaAtual) {
        this.estaminaAtual = estaminaAtual;
    }

    public Long getVelocidadePadrao() {
        return velocidadePadrao;
    }

    public void setVelocidadePadrao(Long velocidadePadrao) {
        this.velocidadePadrao = velocidadePadrao;
    }

    public Long getSaldoBanco() {
        return saldoBanco;
    }

    public void setSaldoBanco(Long saldoBanco) {
        this.saldoBanco = saldoBanco;
    }

    public Container getInventario() {
        return inventario;
    }

    public void setInventario(Container inventario) {
        this.inventario = inventario;
    }

    public PlayerPosicao getPosicao() {
        return posicao;
    }

    public void setPosicao(PlayerPosicao posicao) {
        this.posicao = posicao;
    }

    public Long getTotalMortes() {
        return totalMortes;
    }

    public void setTotalMortes(Long totalMortes) {
        this.totalMortes = totalMortes;
    }
    public Long getTotalKillsMobs() {
        return totalKillsMobs;
    }

    public void setTotalKillsMobs(Long totalKillsMobs) {
        this.totalKillsMobs = totalKillsMobs;
    }

    public Long getTotalKillsPlayers() {
        return totalKillsPlayers;
    }

    public void setTotalKillsPlayers(Long totalKillsPlayers) {
        this.totalKillsPlayers = totalKillsPlayers;
    }

    public LocalDateTime getUltimaMorte() {
        return ultimaMorte;
    }

    public void setUltimaMorte(LocalDateTime ultimaMorte) {
        this.ultimaMorte = ultimaMorte;
    }
}
