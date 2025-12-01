package org.cupula.model.islands.acessoilhas;

import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.Structure;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaAcesso extends EntityClass {
    
    private Ilha ilhaOrigem;
    private Ilha ilhaDestino;
    private Structure estrutura;
    private Long tempoViagem;    
}
