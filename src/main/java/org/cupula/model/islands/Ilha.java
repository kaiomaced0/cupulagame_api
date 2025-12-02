package org.cupula.model.islands;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.acessoilhas.IlhaAcesso;
import org.cupula.model.islands.enums.IlhaTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

@Entity
public class Ilha extends EntityClass {

    private Long tamanhoX;
    private Long tamanhoY;

    private Long pontoInicialX;
    private Long pontoInicialY;
    private Long pontoFinalX;
    private Long pontoFinalY;
    
    @Enumerated(EnumType.STRING)
    private IlhaTipo tipo;

    @OneToMany
    private List<Sector> setores;

    @OneToMany
    private List<IlhaAcesso> acesso;
    
}
