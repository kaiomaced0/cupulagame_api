package org.cupula.model.islands;

import java.util.List;

import org.cupula.model.islands.acessoilhas.IlhaAcesso;
import org.cupula.model.islands.enums.IlhaTipo;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Ilha extends EntityClass {

    private Long tamanhoX;
    private Long tamanhoY;

    private Long pontoInicialX;
    private Long pontoInicialY;
    private Long pontoFinalX;
    private Long pontoFinalY;
    
    private IlhaTipo tipo;
    private List<Sector> setores;

    private List<IlhaAcesso> acesso;
    
}
