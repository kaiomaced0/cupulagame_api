package org.cupula.model.items;

import org.cupula.model.items.enums.ItemTier;
import org.cupula.model.structures.ItemStructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;

import org.cupula.model.EntityClass;

import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Item extends EntityClass {

    @OneToOne
    @JoinColumn(name = "item_structure_id")
    private ItemStructure itemStructure;
    @ManyToOne
    @JoinColumn(name = "item_tipo_id")
    private ItemTipo itemTipo;

    private String nome;
    private String descricao;

    private Long quantidade;
    private Long durabilidadeAtual;
    @Enumerated(EnumType.STRING)
    private ItemTier tier;

    private Long resistenciaFogo;
    private Long resistenciaAgua;
    private Long resistenciaChoque;
    private Long resistenciaMagica;

    private Long durability_current;
    private Long durability_max;
    
    private Long danoMinimo;
    private Long danoMaximo;

    private Long danoMagicoMinimo;
    private Long danoMagicoMaximo;
    private Long criticoChance;
    private Long criticoMultiplicador;

    private Long range;
    
    private Long chanceAcerto;

    private Long buffVida;
    private Long buffMana;
    private Long buffStamina;
    private Long buffForca;
    private Long buffInteligencia;
    private Long buffResistencia;
    private Long buffSorte;
    private Long buffVelocidade;
    
    public ItemStructure getItemStructure() {
        return itemStructure;
    }
    public void setItemStructure(ItemStructure itemStructure) {
        this.itemStructure = itemStructure;
    }
    public ItemTipo getItemTipo() {
        return itemTipo;
    }
    public void setItemTipo(ItemTipo itemTipo) {
        this.itemTipo = itemTipo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Long getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
    public Long getDurabilidadeAtual() {
        return durabilidadeAtual;
    }
    public void setDurabilidadeAtual(Long durabilidadeAtual) {
        this.durabilidadeAtual = durabilidadeAtual;
    }
    public ItemTier getTier() {
        return tier;
    }
    public void setTier(ItemTier tier) {
        this.tier = tier;
    }
    public Long getResistenciaFogo() {
        return resistenciaFogo;
    }
    public void setResistenciaFogo(Long resistenciaFogo) {
        this.resistenciaFogo = resistenciaFogo;
    }
    public Long getResistenciaAgua() {
        return resistenciaAgua;
    }
    public void setResistenciaAgua(Long resistenciaAgua) {
        this.resistenciaAgua = resistenciaAgua;
    }
    public Long getResistenciaChoque() {
        return resistenciaChoque;
    }
    public void setResistenciaChoque(Long resistenciaChoque) {
        this.resistenciaChoque = resistenciaChoque;
    }
    public Long getResistenciaMagica() {
        return resistenciaMagica;
    }
    public void setResistenciaMagica(Long resistenciaMagica) {
        this.resistenciaMagica = resistenciaMagica;
    }
    public Long getDurability_current() {
        return durability_current;
    }
    public void setDurability_current(Long durability_current) {
        this.durability_current = durability_current;
    }
    public Long getDurability_max() {
        return durability_max;
    }
    public void setDurability_max(Long durability_max) {
        this.durability_max = durability_max;
    }
    public Long getDanoMinimo() {
        return danoMinimo;
    }
    public void setDanoMinimo(Long danoMinimo) {
        this.danoMinimo = danoMinimo;
    }
    public Long getDanoMaximo() {
        return danoMaximo;
    }
    public void setDanoMaximo(Long danoMaximo) {
        this.danoMaximo = danoMaximo;
    }
    public Long getDanoMagicoMinimo() {
        return danoMagicoMinimo;
    }
    public void setDanoMagicoMinimo(Long danoMagicoMinimo) {
        this.danoMagicoMinimo = danoMagicoMinimo;
    }
    public Long getDanoMagicoMaximo() {
        return danoMagicoMaximo;
    }
    public void setDanoMagicoMaximo(Long danoMagicoMaximo) {
        this.danoMagicoMaximo = danoMagicoMaximo;
    }
    public Long getCriticoChance() {
        return criticoChance;
    }
    public void setCriticoChance(Long criticoChance) {
        this.criticoChance = criticoChance;
    }
    public Long getCriticoMultiplicador() {
        return criticoMultiplicador;
    }
    public void setCriticoMultiplicador(Long criticoMultiplicador) {
        this.criticoMultiplicador = criticoMultiplicador;
    }
    public Long getRange() {
        return range;
    }
    public void setRange(Long range) {
        this.range = range;
    }
    public Long getChanceAcerto() {
        return chanceAcerto;
    }
    public void setChanceAcerto(Long chanceAcerto) {
        this.chanceAcerto = chanceAcerto;
    }
    public Long getBuffVida() {
        return buffVida;
    }
    public void setBuffVida(Long buffVida) {
        this.buffVida = buffVida;
    }
    public Long getBuffMana() {
        return buffMana;
    }
    public void setBuffMana(Long buffMana) {
        this.buffMana = buffMana;
    }
    public Long getBuffStamina() {
        return buffStamina;
    }
    public void setBuffStamina(Long buffStamina) {
        this.buffStamina = buffStamina;
    }
    public Long getBuffForca() {
        return buffForca;
    }
    public void setBuffForca(Long buffForca) {
        this.buffForca = buffForca;
    }
    public Long getBuffInteligencia() {
        return buffInteligencia;
    }
    public void setBuffInteligencia(Long buffInteligencia) {
        this.buffInteligencia = buffInteligencia;
    }
    public Long getBuffResistencia() {
        return buffResistencia;
    }
    public void setBuffResistencia(Long buffResistencia) {
        this.buffResistencia = buffResistencia;
    }
    public Long getBuffSorte() {
        return buffSorte;
    }
    public void setBuffSorte(Long buffSorte) {
        this.buffSorte = buffSorte;
    }
    public Long getBuffVelocidade() {
        return buffVelocidade;
    }
    public void setBuffVelocidade(Long buffVelocidade) {
        this.buffVelocidade = buffVelocidade;
    }

}
