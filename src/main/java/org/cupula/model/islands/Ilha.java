package org.cupula.model.islands;

import java.util.List;

import org.cupula.model.islands.acessoilhas.IlhaAcesso;
import org.cupula.model.islands.enums.IlhaTipo;

public class Ilha {

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
