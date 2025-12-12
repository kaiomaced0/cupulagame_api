package org.cupula.model.pvp.aposta;

import java.util.List;

import org.cupula.model.EntityClass;
import org.cupula.model.entities.player.Player;
import org.cupula.model.items.Item;
import org.cupula.model.pvp.Pvp;
import org.cupula.model.pvp.aposta.enums.PvpApostaStatus;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class PvpAposta extends EntityClass {
    // preciso adicionar a transacao de itens de aposta! elas precisam ir para um container e ao fim ser entregue os itens ao vencedor.

    @ManyToMany
    @JoinTable(
        name = "pvp_aposta_itens_desafiante",
        joinColumns = @JoinColumn(name = "pvp_aposta_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itensApostadosPeloDesafiante;
    
    @ManyToMany
    @JoinTable(
        name = "pvp_aposta_itens_desafiado",
        joinColumns = @JoinColumn(name = "pvp_aposta_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itensApostadosPeloDesafiado;
    
    private Long quantidadeMoedasPeloDesafiante;
    private Long quantidadeMoedasPeloDesafiado;
    
    private Boolean aceitaApostaDesafiante;
    private Boolean aceitaApostaDesafiado;
    
    @ManyToOne
    private Player desafiante;
    
    @ManyToOne
    private Player desafiado;

    @OneToOne
    private Pvp pvp;
    
    @Enumerated(EnumType.STRING)
    private PvpApostaStatus status;

    // Getters e Setters
    public List<Item> getItensApostadosPeloDesafiante() {
        return itensApostadosPeloDesafiante;
    }

    public void setItensApostadosPeloDesafiante(List<Item> itensApostadosPeloDesafiante) {
        this.itensApostadosPeloDesafiante = itensApostadosPeloDesafiante;
    }

    public List<Item> getItensApostadosPeloDesafiado() {
        return itensApostadosPeloDesafiado;
    }

    public void setItensApostadosPeloDesafiado(List<Item> itensApostadosPeloDesafiado) {
        this.itensApostadosPeloDesafiado = itensApostadosPeloDesafiado;
    }

    public Long getQuantidadeMoedasPeloDesafiante() {
        return quantidadeMoedasPeloDesafiante;
    }

    public void setQuantidadeMoedasPeloDesafiante(Long quantidadeMoedasPeloDesafiante) {
        this.quantidadeMoedasPeloDesafiante = quantidadeMoedasPeloDesafiante;
    }

    public Long getQuantidadeMoedasPeloDesafiado() {
        return quantidadeMoedasPeloDesafiado;
    }

    public void setQuantidadeMoedasPeloDesafiado(Long quantidadeMoedasPeloDesafiado) {
        this.quantidadeMoedasPeloDesafiado = quantidadeMoedasPeloDesafiado;
    }

    public Boolean getAceitaApostaDesafiante() {
        return aceitaApostaDesafiante;
    }

    public void setAceitaApostaDesafiante(Boolean aceitaApostaDesafiante) {
        this.aceitaApostaDesafiante = aceitaApostaDesafiante;
    }

    public Boolean getAceitaApostaDesafiado() {
        return aceitaApostaDesafiado;
    }

    public void setAceitaApostaDesafiado(Boolean aceitaApostaDesafiado) {
        this.aceitaApostaDesafiado = aceitaApostaDesafiado;
    }

    public Player getDesafiante() {
        return desafiante;
    }

    public void setDesafiante(Player desafiante) {
        this.desafiante = desafiante;
    }

    public Player getDesafiado() {
        return desafiado;
    }

    public void setDesafiado(Player desafiado) {
        this.desafiado = desafiado;
    }

    public PvpApostaStatus getStatus() {
        return status;
    }

    public void setStatus(PvpApostaStatus status) {
        this.status = status;
    }
    public Pvp getPvp() {
        return pvp;
    }
    public void setPvp(Pvp pvp) {
        this.pvp = pvp;
    }

}
