package org.cupula.model.islands.acessoilhas;

import org.cupula.model.EntityClass;
import org.cupula.model.islands.Ilha;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ilha_posicao")
public class IlhaPosicao extends EntityClass {
    // essa tabela sera mais um mapeamento de posicoes das ilhas para verificar se faz sentido uma ilha A ter uma IlhaAcesso a ilha B

    @ManyToOne
    @JoinColumn(name = "ilha_id")
    private Ilha ilha;
    
 // esses x e y nao sao posicoes reais, sao regiores como um mapa geral mesmo, nao vai seguir a proporcao do jogo. 
 // uma ilha pode ocupar 1,1 ate 2,1 e outra 1,2 ate 5,2. Assim, a segunda ilha Ã© bem maior que a primeira e pode ter mais pontosAcesso
    private Long xInicial;
    private Long xFinal;
    private Long yInicial;
    private Long yFinal;
    
    public Ilha getIlha() {
        return ilha;
    }
    public void setIlha(Ilha ilha) {
        this.ilha = ilha;
    }
    public Long getXInicial() {
        return xInicial;
    }
    public void setXInicial(Long xInicial) {
        this.xInicial = xInicial;
    }
    public Long getXFinal() {
        return xFinal;
    }
    public void setXFinal(Long xFinal) {
        this.xFinal = xFinal;
    }
    public Long getYInicial() {
        return yInicial;
    }
    public void setYInicial(Long yInicial) {
        this.yInicial = yInicial;
    }
    public Long getYFinal() {
        return yFinal;
    }
    public void setYFinal(Long yFinal) {
        this.yFinal = yFinal;
    }

}
