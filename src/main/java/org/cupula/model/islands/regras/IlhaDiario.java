package org.cupula.model.islands.regras;

import java.util.List;

import org.cupula.model.islands.Ilha;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class IlhaDiario extends EntityClass {
    
    private Ilha ilha;
    private List<IlhaDiarioAcontecimentos> acontecimentos;
}
