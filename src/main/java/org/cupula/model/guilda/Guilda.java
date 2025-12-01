package org.cupula.model.guilda;

import java.util.List;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Guilda extends EntityClass {

    private String nome;
    private String descricao;

    private String tag;
    private Long nivel;
    private Long xp;
    private Long saldoBanco;

    private List<GuildaTag> tags;
    private List<GuildaConquista> conquistas;
    

}
