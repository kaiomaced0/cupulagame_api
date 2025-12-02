package org.cupula.model.islands.acessoilhas;

import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.Structure;

import org.cupula.model.EntityClass;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IlhaAcesso extends EntityClass {
    
    @ManyToOne
    private Ilha ilhaOrigem;
    @ManyToOne
    private Ilha ilhaDestino;
    @ManyToOne
    private Structure estrutura;
    private Long tempoViagem;    
}
