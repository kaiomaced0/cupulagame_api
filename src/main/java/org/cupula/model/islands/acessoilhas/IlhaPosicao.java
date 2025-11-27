package org.cupula.model.islands.acessoilhas;

import org.cupula.model.islands.Ilha;

public class IlhaPosicao {
    // essa tabela sera mais um mapeamento de posicoes das ilhas para verificar se faz sentido uma ilha A ter uma IlhaAcesso a ilha B

    private Ilha ilha;
    
 // esses x e y nao sao posicoes reais, sao regiores como um mapa geral mesmo, nao vai seguir a proporcao do jogo. 
 // uma ilha pode ocupar 1,1 ate 2,1 e outra 1,2 ate 5,2. Assim, a segunda ilha é bem maior que a primeira e pode ter mais pontosAcesso
    private Long xInicial;
    private Long xFinal;
    private Long yInicial;
    private Long yFinal;
    
}
