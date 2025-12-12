package org.cupula.model.entities.player;

import org.cupula.model.EntityClass;
import org.cupula.model.auth.Usuario;
import org.cupula.model.comunity.VisibilidadePerfil;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.islands.Ilha;
import org.cupula.model.structures.view.ColorMaterial;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Player extends EntityClass {
    //Atributos Basicos visuais e de tamanho

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private VisibilidadePerfil visibilidade = VisibilidadePerfil.AMIGOS;

    @Enumerated(EnumType.STRING)
    private PlayerRaca raca;
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

    @OneToOne
    @JoinColumn(name = "status_id")
    private PlayerStatus status;

    @ManyToOne
    @JoinColumn(name = "ilha_atual_id")
    private Ilha ilhaAtual;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public VisibilidadePerfil getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(VisibilidadePerfil visibilidade) {
        this.visibilidade = visibilidade;
    }

    public PlayerRaca getRaca() {
        return raca;
    }

    public void setRaca(PlayerRaca raca) {
        this.raca = raca;
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

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public Ilha getIlhaAtual() {
        return ilhaAtual;
    }

    public void setIlhaAtual(Ilha ilhaAtual) {
        this.ilhaAtual = ilhaAtual;
    }
    
}
