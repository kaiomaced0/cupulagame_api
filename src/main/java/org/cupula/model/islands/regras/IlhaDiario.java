package org.cupula.model.islands.regras;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.Ilha;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class IlhaDiario extends EntityClass {
    
    @ManyToOne
    private Ilha ilha;
    @OneToMany
    private List<IlhaDiarioAcontecimentos> acontecimentos;
    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }
    public List<IlhaDiarioAcontecimentos> getAcontecimentos() {
        return acontecimentos;
    }
    public void setAcontecimentos(List<IlhaDiarioAcontecimentos> acontecimentos) {
        this.acontecimentos = acontecimentos;
    }

}
