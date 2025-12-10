package org.cupula.model.entities.baseview;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;

@Entity
public class PlayerTipoCabelo extends EntityClass {
    private String nome;
    private String path;
    private Long possibilidade;
    private PlayerRaca playerRaca;
    private Structure estrutura;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Long getPossibilidade() {
        return possibilidade;
    }
    public void setPossibilidade(Long possibilidade) {
        this.possibilidade = possibilidade;
    }
    public PlayerRaca getPlayerRaca() {
        return playerRaca;
    }
    public void setPlayerRaca(PlayerRaca playerRaca) {
        this.playerRaca = playerRaca;
    }
    public Structure getEstrutura() {
        return estrutura;
    }
    public void setEstrutura(Structure estrutura) {
        this.estrutura = estrutura;
    }

}
