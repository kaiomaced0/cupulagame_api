package org.cupula.model.banco;

import org.cupula.model.banco.enums.ContaBancariaTipo;
import org.cupula.model.empresa.Empresa;
import org.cupula.model.entities.Player;
import org.cupula.model.guilda.ContratoGuilda;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.islands.Ilha;
import org.cupula.model.market.services.Contrato;
import org.cupula.model.market.services.MarketService;

public class ContaBancaria {
    
    private String numeroConta;
    private String titular;
    private Long saldo;

    private ContaBancariaTipo tipo;

    private Player proprietario;

    private Guilda guildaVinculada;
    private ContratoGuilda contratoGuildaVinculado;

    private Ilha ilhaVinculada;
    private Empresa empresaVinculada;

    private Contrato contratoMarketVinculado;

}
