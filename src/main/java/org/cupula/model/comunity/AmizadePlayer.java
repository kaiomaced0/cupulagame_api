package org.cupula.model.comunity;

import java.time.LocalDateTime;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class AmizadePlayer extends EntityClass {

    @ManyToOne
    private Player solicitante;

    @ManyToOne
    private Player solicitado;

    @Enumerated(EnumType.STRING)
    private StatusAmizade status;

    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataResposta;

    public AmizadePlayer() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = StatusAmizade.PENDENTE;
    }

    public Player getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Player solicitante) {
        this.solicitante = solicitante;
    }

    public Player getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Player solicitado) {
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
}
