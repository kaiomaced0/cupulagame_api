package org.cupula.model.islands.cargos;

import org.cupula.model.entities.Player;
import org.cupula.model.islands.Ilha;

public class IlhaMembro {
    
    private Ilha ilha;
    private Player player;

    private Long dataEntrada;
    private Long dataUltimaAtividade;
    private Boolean isOnline;

    private Long minutosDisponiveisParaIlhaSemanalmente;

    private String apelido;
}
