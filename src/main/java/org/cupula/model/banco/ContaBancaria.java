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

    public String getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public Long getSaldo() {
        return saldo;
    }
    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }
    public ContaBancariaTipo getTipo() {
        return tipo;
    }
    public void setTipo(ContaBancariaTipo tipo) {
        this.tipo = tipo;
    }
    public Player getProprietario() {
        return proprietario;
    }
    public void setProprietario(Player proprietario) {
        this.proprietario = proprietario;
    }
    public Guilda getGuildaVinculada() {
        return guildaVinculada;
    }
    public void setGuildaVinculada(Guilda guildaVinculada) {
        this.guildaVinculada = guildaVinculada;
    }
    public ContratoGuilda getContratoGuildaVinculado() {
        return contratoGuildaVinculado;
    }
    public void setContratoGuildaVinculado(ContratoGuilda contratoGuildaVinculado) {
        this.contratoGuildaVinculado = contratoGuildaVinculado;
    }
    public Ilha getIlhaVinculada() {
        return ilhaVinculada;
    }
    public void setIlhaVinculada(Ilha ilhaVinculada) {
        this.ilhaVinculada = ilhaVinculada;
    }
    public Empresa getEmpresaVinculada() {
        return empresaVinculada;
    }
    public void setEmpresaVinculada(Empresa empresaVinculada) {
        this.empresaVinculada = empresaVinculada;
    }
    public IlhaDepartamento getIlhaDepartamento() {
        return ilhaDepartamento;
    }
    public void setIlhaDepartamento(IlhaDepartamento ilhaDepartamento) {
        this.ilhaDepartamento = ilhaDepartamento;
    }
    public Contrato getContratoMarketVinculado() {
        return contratoMarketVinculado;
    }
    public void setContratoMarketVinculado(Contrato contratoMarketVinculado) {
        this.contratoMarketVinculado = contratoMarketVinculado;
    }

}
