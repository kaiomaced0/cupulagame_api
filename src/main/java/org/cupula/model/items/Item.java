package org.cupula.model.items;

import org.cupula.model.items.enums.ItemTier;
import org.cupula.model.structures.ItemStructure;

import jakarta.persistence.Entity;
import org.cupula.model.EntityClass;

@Entity
public class Item extends EntityClass {

    private ItemStructure itemStructure;
    private ItemTipo itemTipo;
    private String nome;
    private String descricao;

    private Long quantidade;
    private Long durabilidadeAtual;
    
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
    

    
}
