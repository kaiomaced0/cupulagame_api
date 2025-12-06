package org.cupula.model.guilda.negociacao;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;
import org.cupula.model.guilda.Guilda;

import jakarta.persistence.Entity;

@Entity
public class NegociacaoServicoGuildaMensagem extends EntityClass {
    private String mensagemCriptografada;
    private Player remetente;
    private Guilda guilda;
    
}
