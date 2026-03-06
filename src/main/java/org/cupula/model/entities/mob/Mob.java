package org.cupula.model.entities.mob;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.containers.Container;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.MobTipo;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "mob")
public class Mob extends EntityClass {
    
    @OneToMany
    @JoinColumn(name = "mob_id")
    private List<Container> containers;

    @OneToOne
    @JoinColumn(name = "posicao_id")
    private MobPosicao posicao;
    
    @Enumerated(EnumType.STRING)
    private MobTipo tipo;

    // Atributos visuais
    private Long tamanhoX;
    private Long tamanhoY;
    private Long tamanhoZ;
    
    @ManyToOne
    @JoinColumn(name = "tipo_cabelo_id")
    private PlayerTipoCabelo tipoCabelo;

    @ManyToOne
    @JoinColumn(name = "cor_pele_id")
    private ColorMaterial corPele;

    private Long tamanhoXOrelha;
    private Long tamanhoYOrelha;
    private Long tamanhoZOrelha;
    
    @ManyToOne
    @JoinColumn(name = "cor_orelha_id")
    private ColorMaterial corOrelha;

    // Atributos de combate
    private Double velocidade;
    private Long hp;
    private Double armadura;
    private Double mr; // Magic Resist
    private Double ad; // Attack Damage
    private Double ap; // Ability Power
    private Double attackSpeed; // Attack Speed
    private Integer critico; // 0-100 (porcentagem)
    private Double alcanceAtaque;
    private Long mana;
    private Double regeneracaoMana; // Porcentagem por segundo

    public List<Container> getContainers() {
        return containers;
    }
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
    public MobPosicao getPosicao() {
        return posicao;
    }
    public void setPosicao(MobPosicao posicao) {
        this.posicao = posicao;
    }
    public MobTipo getTipo() {
        return tipo;
    }
    public void setTipo(MobTipo tipo) {
        this.tipo = tipo;
    }
    public Long getTamanhoX() {
        return tamanhoX;
    }
    public void setTamanhoX(Long tamanhoX) {
        this.tamanhoX = tamanhoX;
    }
    public Long getTamanhoY() {
        return tamanhoY;
    }
    public void setTamanhoY(Long tamanhoY) {
        this.tamanhoY = tamanhoY;
    }
    public Long getTamanhoZ() {
        return tamanhoZ;
    }
    public void setTamanhoZ(Long tamanhoZ) {
        this.tamanhoZ = tamanhoZ;
    }
    public PlayerTipoCabelo getTipoCabelo() {
        return tipoCabelo;
    }
    public void setTipoCabelo(PlayerTipoCabelo tipoCabelo) {
        this.tipoCabelo = tipoCabelo;
    }
    public ColorMaterial getCorPele() {
        return corPele;
    }
    public void setCorPele(ColorMaterial corPele) {
        this.corPele = corPele;
    }
    public Long getTamanhoXOrelha() {
        return tamanhoXOrelha;
    }
    public void setTamanhoXOrelha(Long tamanhoXOrelha) {
        this.tamanhoXOrelha = tamanhoXOrelha;
    }
    public Long getTamanhoYOrelha() {
        return tamanhoYOrelha;
    }
    public void setTamanhoYOrelha(Long tamanhoYOrelha) {
        this.tamanhoYOrelha = tamanhoYOrelha;
    }
    public Long getTamanhoZOrelha() {
        return tamanhoZOrelha;
    }
    public void setTamanhoZOrelha(Long tamanhoZOrelha) {
        this.tamanhoZOrelha = tamanhoZOrelha;
    }
    public ColorMaterial getCorOrelha() {
        return corOrelha;
    }
    public void setCorOrelha(ColorMaterial corOrelha) {
        this.corOrelha = corOrelha;
    }
    public Double getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(Double velocidade) {
        this.velocidade = velocidade;
    }
    public Long getHp() {
        return hp;
    }
    public void setHp(Long hp) {
        this.hp = hp;
    }
    public Double getArmadura() {
        return armadura;
    }
    public void setArmadura(Double armadura) {
        this.armadura = armadura;
    }
    public Double getMr() {
        return mr;
    }
    public void setMr(Double mr) {
        this.mr = mr;
    }
    public Double getAd() {
        return ad;
    }
    public void setAd(Double ad) {
        this.ad = ad;
    }
    public Double getAp() {
        return ap;
    }
    public void setAp(Double ap) {
        this.ap = ap;
    }
    public Double getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackSpeed(Double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    public Integer getCritico() {
        return critico;
    }
    public void setCritico(Integer critico) {
        this.critico = critico;
    }
    public Double getAlcanceAtaque() {
        return alcanceAtaque;
    }
    public void setAlcanceAtaque(Double alcanceAtaque) {
        this.alcanceAtaque = alcanceAtaque;
    }
    public Long getMana() {
        return mana;
    }
    public void setMana(Long mana) {
        this.mana = mana;
    }
    public Double getRegeneracaoMana() {
        return regeneracaoMana;
    }
    public void setRegeneracaoMana(Double regeneracaoMana) {
        this.regeneracaoMana = regeneracaoMana;
    }

}
