package org.cupula.model.islands.ilhaloja;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class FuncionarioLoja extends EntityClass {
    
    @ManyToOne
    private IlhaLoja loja;
    
    @ManyToOne
    private Player funcionario;

    // Getters e Setters
    public IlhaLoja getLoja() {
        return loja;
    }

    public void setLoja(IlhaLoja loja) {
        this.loja = loja;
    }

    public Player getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Player funcionario) {
        this.funcionario = funcionario;
    }
}
