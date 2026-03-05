package org.cupula.model.baseatributo.combate;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.enums.PlayerRaca;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity(name = "player_base_atributo_combate")
public class PlayerBaseAtributoCombate extends EntityClass {
    
    @Enumerated(EnumType.STRING)
    private PlayerRaca raca;

    // Velocidade
    private Double velocidadeMinimo;
    private Double velocidadeVariacao; // Porcentagem de variação

    // HP
    private Long hpMinimo;
    private Double hpVariacao;

    // Armadura
    private Double armaduraMinimo;
    private Double armaduraVariacao;

    // Magic Resist
    private Double mrMinimo;
    private Double mrVariacao;

    // Attack Damage
    private Double adMinimo;
    private Double adVariacao;

    // Ability Power
    private Double apMinimo;
    private Double apVariacao;

    // Attack Speed
    private Double asMinimo;
    private Double asVariacao;

    // Crítico (0-100)
    private Integer criticoMinimo;
    private Double criticoVariacao;

    // Alcance de Ataque
    private Double alcanceAtaqueMinimo;
    private Double alcanceAtaqueVariacao;

    // Mana
    private Long manaMinimo;
    private Double manaVariacao;

    // Regeneração de Mana (porcentagem por segundo)
    private Double regeneracaoManaMinimo;
    private Double regeneracaoManaVariacao;

    public PlayerRaca getRaca() {
        return raca;
    }

    public void setRaca(PlayerRaca raca) {
        this.raca = raca;
    }

    public Double getVelocidadeMinimo() {
        return velocidadeMinimo;
    }

    public void setVelocidadeMinimo(Double velocidadeMinimo) {
        this.velocidadeMinimo = velocidadeMinimo;
    }

    public Double getVelocidadeVariacao() {
        return velocidadeVariacao;
    }

    public void setVelocidadeVariacao(Double velocidadeVariacao) {
        this.velocidadeVariacao = velocidadeVariacao;
    }

    public Long getHpMinimo() {
        return hpMinimo;
    }

    public void setHpMinimo(Long hpMinimo) {
        this.hpMinimo = hpMinimo;
    }

    public Double getHpVariacao() {
        return hpVariacao;
    }

    public void setHpVariacao(Double hpVariacao) {
        this.hpVariacao = hpVariacao;
    }

    public Double getArmaduraMinimo() {
        return armaduraMinimo;
    }

    public void setArmaduraMinimo(Double armaduraMinimo) {
        this.armaduraMinimo = armaduraMinimo;
    }

    public Double getArmaduraVariacao() {
        return armaduraVariacao;
    }

    public void setArmaduraVariacao(Double armaduraVariacao) {
        this.armaduraVariacao = armaduraVariacao;
    }

    public Double getMrMinimo() {
        return mrMinimo;
    }

    public void setMrMinimo(Double mrMinimo) {
        this.mrMinimo = mrMinimo;
    }

    public Double getMrVariacao() {
        return mrVariacao;
    }

    public void setMrVariacao(Double mrVariacao) {
        this.mrVariacao = mrVariacao;
    }

    public Double getAdMinimo() {
        return adMinimo;
    }

    public void setAdMinimo(Double adMinimo) {
        this.adMinimo = adMinimo;
    }

    public Double getAdVariacao() {
        return adVariacao;
    }

    public void setAdVariacao(Double adVariacao) {
        this.adVariacao = adVariacao;
    }

    public Double getApMinimo() {
        return apMinimo;
    }

    public void setApMinimo(Double apMinimo) {
        this.apMinimo = apMinimo;
    }

    public Double getApVariacao() {
        return apVariacao;
    }

    public void setApVariacao(Double apVariacao) {
        this.apVariacao = apVariacao;
    }

    public Double getAsMinimo() {
        return asMinimo;
    }

    public void setAsMinimo(Double asMinimo) {
        this.asMinimo = asMinimo;
    }

    public Double getAsVariacao() {
        return asVariacao;
    }

    public void setAsVariacao(Double asVariacao) {
        this.asVariacao = asVariacao;
    }

    public Integer getCriticoMinimo() {
        return criticoMinimo;
    }

    public void setCriticoMinimo(Integer criticoMinimo) {
        this.criticoMinimo = criticoMinimo;
    }

    public Double getCriticoVariacao() {
        return criticoVariacao;
    }

    public void setCriticoVariacao(Double criticoVariacao) {
        this.criticoVariacao = criticoVariacao;
    }

    public Double getAlcanceAtaqueMinimo() {
        return alcanceAtaqueMinimo;
    }

    public void setAlcanceAtaqueMinimo(Double alcanceAtaqueMinimo) {
        this.alcanceAtaqueMinimo = alcanceAtaqueMinimo;
    }

    public Double getAlcanceAtaqueVariacao() {
        return alcanceAtaqueVariacao;
    }

    public void setAlcanceAtaqueVariacao(Double alcanceAtaqueVariacao) {
        this.alcanceAtaqueVariacao = alcanceAtaqueVariacao;
    }

    public Long getManaMinimo() {
        return manaMinimo;
    }

    public void setManaMinimo(Long manaMinimo) {
        this.manaMinimo = manaMinimo;
    }

    public Double getManaVariacao() {
        return manaVariacao;
    }

    public void setManaVariacao(Double manaVariacao) {
        this.manaVariacao = manaVariacao;
    }

    public Double getRegeneracaoManaMinimo() {
        return regeneracaoManaMinimo;
    }

    public void setRegeneracaoManaMinimo(Double regeneracaoManaMinimo) {
        this.regeneracaoManaMinimo = regeneracaoManaMinimo;
    }

    public Double getRegeneracaoManaVariacao() {
        return regeneracaoManaVariacao;
    }

    public void setRegeneracaoManaVariacao(Double regeneracaoManaVariacao) {
        this.regeneracaoManaVariacao = regeneracaoManaVariacao;
    }
}
