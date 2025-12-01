package org.cupula.model.guilda;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class GuildaConquista extends EntityClass {
    private String nome;
    private String descricao;
    private String iconePath;
}
