package org.cupula.model.islands;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.acessoilhas.IlhaAcesso;
import org.cupula.model.islands.enums.IlhaTipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

@Entity(name = "ilha")
public class Ilha extends EntityClass {

    private Long tamanhoX;
    private Long tamanhoY;

    private Long pontoInicialX;
    private Long pontoInicialY;
    private Long pontoFinalX;
    private Long pontoFinalY;
    
    @Enumerated(EnumType.STRING)
    private IlhaTipo tipo;

    @OneToMany
    private List<Sector> setores;

    @OneToMany
    private List<IlhaAcesso> acesso;
    
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
    public Long getPontoInicialX() {
        return pontoInicialX;
    }
    public void setPontoInicialX(Long pontoInicialX) {
        this.pontoInicialX = pontoInicialX;
    }
    public Long getPontoInicialY() {
        return pontoInicialY;
    }
    public void setPontoInicialY(Long pontoInicialY) {
        this.pontoInicialY = pontoInicialY;
    }
    public Long getPontoFinalX() {
        return pontoFinalX;
    }
    public void setPontoFinalX(Long pontoFinalX) {
        this.pontoFinalX = pontoFinalX;
    }
    public Long getPontoFinalY() {
        return pontoFinalY;
    }
    public void setPontoFinalY(Long pontoFinalY) {
        this.pontoFinalY = pontoFinalY;
    }
    public IlhaTipo getTipo() {
        return tipo;
    }
    public void setTipo(IlhaTipo tipo) {
        this.tipo = tipo;
    }
    public List<Sector> getSetores() {
        return setores;
    }
    public void setSetores(List<Sector> setores) {
        this.setores = setores;
    }
    public List<IlhaAcesso> getAcesso() {
        return acesso;
    }
    public void setAcesso(List<IlhaAcesso> acesso) {
        this.acesso = acesso;
    }

}
