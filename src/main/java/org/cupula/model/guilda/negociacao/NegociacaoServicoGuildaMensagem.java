package org.cupula.model.guilda.negociacao;

import org.cupula.model.entities.Player;
import org.cupula.model.guilda.Guilda;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class NegociacaoServicoGuildaMensagem extends EntityClass {
    private String mensagemCriptografada;
    private Player remetente;
    private Guilda guilda;
    
}
