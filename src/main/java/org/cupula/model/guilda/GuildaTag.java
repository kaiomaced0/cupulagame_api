package org.cupula.model.guilda;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class GuildaTag extends EntityClass {
    
    private String nome;
    private String descricao;
    private String path;
}
