package org.cupula.model.banco;

import org.cupula.model.banco.enums.ContaBancariaTipo;
import org.cupula.model.empresa.Empresa;
import org.cupula.model.entities.Player;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.guilda.contrato.ContratoGuilda;
import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.cargos.IlhaDepartamento;
import org.cupula.model.market.services.Contrato;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class ContaBancaria extends EntityClass {
    
    private String numeroConta;
    private String titular;
    private Long saldo;

    private ContaBancariaTipo tipo;

    private Player proprietario;

    private Guilda guildaVinculada;
    private ContratoGuilda contratoGuildaVinculado;

    private Ilha ilhaVinculada;
    private Empresa empresaVinculada;
    private IlhaDepartamento ilhaDepartamento;

    private Contrato contratoMarketVinculado;

}
