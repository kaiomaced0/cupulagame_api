package org.cupula.model.banco;

import org.cupula.model.EntityClass;
import org.cupula.model.banco.enums.ContaBancariaTipo;
import org.cupula.model.empresa.Empresa;
import org.cupula.model.entities.player.Player;
import org.cupula.model.guilda.Guilda;
import org.cupula.model.guilda.contrato.ContratoGuilda;
import org.cupula.model.islands.Ilha;
import org.cupula.model.islands.cargos.IlhaDepartamento;
import org.cupula.model.market.services.Contrato;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Entity
public class ContaBancaria extends EntityClass {
    
    private String numeroConta;
    private String titular;
    private Long saldo;

    @Enumerated(EnumType.STRING)
    private ContaBancariaTipo tipo;

    @ManyToOne
    private Player proprietario;

    @ManyToOne
    private Guilda guildaVinculada;
    @ManyToOne
    private ContratoGuilda contratoGuildaVinculado;

    @ManyToOne
    private Ilha ilhaVinculada;
    @ManyToOne
    private Empresa empresaVinculada;
    @ManyToOne
    private IlhaDepartamento ilhaDepartamento;

    @ManyToOne
    private Contrato contratoMarketVinculado;

}
