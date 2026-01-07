package org.cupula.model.islands.ilhaloja;

import java.util.List;

import org.cupula.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "ilha_loja_market")
public class IlhaLojaMarket extends EntityClass {
    
    // para o player comprar da loja
    @ManyToOne
    private IlhaLoja loja;
    
    @OneToMany
    private List<IlhaLojaEstoque> tiposAceitos;
    
    private Long price;
    private int quantity;

    // Getters e Setters
    public IlhaLoja getLoja() {
        return loja;
    }

    public void setLoja(IlhaLoja loja) {
        this.loja = loja;
    }

    public List<IlhaLojaEstoque> getTiposAceitos() {
        return tiposAceitos;
    }

    public void setTiposAceitos(List<IlhaLojaEstoque> tiposAceitos) {
        this.tiposAceitos = tiposAceitos;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
