package org.cupula.model.islands.cargos;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.Player;
import org.cupula.model.islands.Ilha;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaMembro extends EntityClass {
    
    @ManyToOne
    private Ilha ilha;
    @ManyToOne
    private Player player;

    private Long dataEntrada;
    private Long dataUltimaAtividade;
    private Boolean isOnline;

    private Long minutosDisponiveisParaIlhaSemanalmente;

    private String apelido;
}
