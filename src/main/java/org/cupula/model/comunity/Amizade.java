package org.cupula.model.comunity;

import java.time.LocalDateTime;
import java.util.Set;

import org.cupula.model.EntityClass;
import org.cupula.model.auth.Usuario;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity(name = "amizade")
public class Amizade extends EntityClass {

    @ManyToOne
    private Usuario solicitante;

    @ManyToOne
    private Usuario solicitado;

    @Enumerated(EnumType.STRING)
    private StatusAmizade status;

    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataResposta;

    // Controle de Visibilidade - Solicitante
    private Boolean solicitanteUsaPadrao = true;
    
    @ManyToMany
    @JoinTable(
        name = "amizade_players_solicitante",
        joinColumns = @JoinColumn(name = "amizade_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> playersLiberadosPeloSolicitante;

    // Controle de Visibilidade - Solicitado
    private Boolean solicitadoUsaPadrao = true;

    @ManyToMany
    @JoinTable(
        name = "amizade_players_solicitado",
        joinColumns = @JoinColumn(name = "amizade_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> playersLiberadosPeloSolicitado;

    // Construtor padrao
    public Amizade() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = StatusAmizade.PENDENTE;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Usuario getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Usuario solicitado) {
        this.solicitado = solicitado;
    }

    public StatusAmizade getStatus() {
        return status;
    }

    public void setStatus(StatusAmizade status) {
        this.status = status;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDateTime getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDateTime dataResposta) {
        this.dataResposta = dataResposta;
    }

    public Boolean getSolicitanteUsaPadrao() {
        return solicitanteUsaPadrao;
    }

    public void setSolicitanteUsaPadrao(Boolean solicitanteUsaPadrao) {
        this.solicitanteUsaPadrao = solicitanteUsaPadrao;
    }

    public Set<Player> getPlayersLiberadosPeloSolicitante() {
        return playersLiberadosPeloSolicitante;
    }

    public void setPlayersLiberadosPeloSolicitante(Set<Player> playersLiberadosPeloSolicitante) {
        this.playersLiberadosPeloSolicitante = playersLiberadosPeloSolicitante;
    }

    public Boolean getSolicitadoUsaPadrao() {
        return solicitadoUsaPadrao;
    }

    public void setSolicitadoUsaPadrao(Boolean solicitadoUsaPadrao) {
        this.solicitadoUsaPadrao = solicitadoUsaPadrao;
    }

    public Set<Player> getPlayersLiberadosPeloSolicitado() {
        return playersLiberadosPeloSolicitado;
    }

    public void setPlayersLiberadosPeloSolicitado(Set<Player> playersLiberadosPeloSolicitado) {
        this.playersLiberadosPeloSolicitado = playersLiberadosPeloSolicitado;
    }
}
