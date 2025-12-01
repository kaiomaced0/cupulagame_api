# Sistema de Combate e PVP: O Duelo Tático

**Visão Geral:** O combate em "Cúpula" foge do estilo "esmagar botões". É um sistema estratégico, calculado e lento, onde cada decisão via API pode custar a vida do personagem. O combate ocorre em **Turnos Sequenciais** dentro de uma grade tática.

---

## 1. O Estado de Combate (`CombatSession`)

Quando um jogador inicia uma agressão ou é agredido, a API cria uma `CombatSession`.
* **Bloqueio de Mundo:** Os jogadores envolvidos ficam com status `IN_COMBAT`. Eles não podem interagir com NPCs, usar o Mercado ou viajar para outra Ilha até que a sessão termine.
* **A Arena:** Uma área de X por Y metros ao redor do ponto de início é "fechada". Tentar sair dessa área conta como ação de "Fugir" (com chance de falha).

---

## 2. Estrutura do Turno

O combate não é simultâneo. Ele segue uma ordem de **Iniciativa**.

1.  **Cálculo de Iniciativa:** Baseado no atributo `Agilidade` + Modificadores de Equipamento.
2.  **Tempo de Decisão:** Cada jogador tem **X segundos** (ex: 30s) para enviar sua requisição `POST /combat/action`.
    * *Timeout:* Se o jogador não responder a tempo, o personagem entra em modo "Pânico" (Defesa reduzida e não ataca).

---

## 3. Ações Disponíveis (Payload da API)

No seu turno, o jogador deve escolher **1 Ação de Movimento** e **1 Ação Principal**.

### A. Movimentação (`MOVE`)
O jogador escolhe reposicionar-se na grade.
* **Lógica:** Mover-se 1 "casa" (ou metro) consome Stamina.
* **Estratégia:** Essencial para usuários de `RANGED` (Arqueiros) manterem distância e para usuários de `MELEE` encurtarem a distância.
* **Payload:** `{ "action": "MOVE", "direction": "NORTH" }`

### B. Ataque (`ATTACK`)
Tenta causar dano ao oponente.
* **Validação de Alcance:** A API calcula a distância (`Vector3`).
    * Se Arma = `MELEE` (Espada) e distância > 1.5m -> **Erro:** "Alvo fora de alcance".
    * Se Arma = `RANGED` (Arco) -> Aplica-se a penalidade de *drop-off* de dano se estiver muito longe.
* **Cálculo:** `(DanoArma + DanoMunição) * Multiplicadores - DefesaInimiga`.
* **Payload:** `{ "action": "ATTACK", "target_id": 99 }`

### C. Defender (`DEFEND`)
O jogador assume postura defensiva.
* **Efeito:**
    * Aumenta a Defesa/Armadura em X% até o próximo turno.
    * Aumenta a chance de bloquear ataques críticos.
* **Uso:** Ideal quando o oponente está preparando um ataque forte ou quando você está esperando o *cooldown* de uma habilidade.
* **Payload:** `{ "action": "DEFEND" }`

### D. Mensagem Rápida (`SHOUT`)
Comunicação tática ou psicológica (Taunt). Não gasta o turno, é uma "Ação Livre".
* **Funcionamento:** Envia uma mensagem curta que aparece no log de combate do inimigo.
* **Uso:** Tentar intimidar, blefar ou coordenar com aliados (em lutas de grupo).
* **Payload:** `{ "action": "SHOUT", "message": "Largue a arma e viva!" }`

### E. Solicitar Rendição (`SURRENDER`)
A mecânica política do combate.
* **O Pedido:** O jogador levanta as mãos (status `SURRENDERING`).
* **A Decisão do Vencedor:** O oponente recebe uma notificação: "Player X está se rendendo. Aceitar?"
    * **Se Aceitar:** O combate acaba. Ninguém morre. O Vencedor pode, via sistema, exigir "Espólios de Guerra" (ex: "Me dê sua espada e você vive").
    * **Se Recusar:** O combate continua e o jogador que pediu rendição perde o turno (ficou vulnerável).
* **Payload:** `{ "action": "SURRENDER" }`

---

## 4. Resolução Matemática (O Backend)

Quando a ação é enviada, o servidor processa:

1.  **Hit Check (Acerto):**
    $$Chance = \frac{PrecisãoAtacante}{EsquivaDefensor \times DistanciaFactor}$$
    * Se `Random(0,100) > Chance`, o ataque erra ("MISS").

2.  **Mitigação (Armadura):**
    * Se o defensor usou `DEFEND`, dobra o valor da armadura.
    * Dano Recebido = `DanoBruto - (DefesaTotal * PenetracaoArma)`.

3.  **Status Check:**
    * O alvo morreu (`HP <= 0`)? -> Fim de Combate (Loot liberado).
    * O alvo desmaiou (Arma de concussão)? -> Pode ser roubado sem morrer.

---

## 5. Exemplo de Fluxo na API

**Cenário:** Player A (Arqueiro) vs Player B (Guerreiro).

1.  **Turno 1 (Player A):**
    * `MOVE`: Trás (Aumenta distância para 10m).
    * `ATTACK`: Dispara flecha. Acerto Crítico!
    * *API Resposta:* "Você recuou. Sua flecha acertou no ombro de B (40 dano)."

2.  **Turno 1 (Player B):**
    * `SHOUT`: "Covarde! Lute como homem!"
    * `MOVE`: Frente (Diminui distância para 8m).
    * `DEFEND`: Levanta escudo (Sabe que não alcança o arqueiro ainda).
    * *API Resposta:* "Você avança protegido pelo escudo."

3.  **Turno 2 (Player A):**
    * `ATTACK`: Dispara novamente.
    * *Cálculo:* O escudo de B reduziu o dano em 80%. Dano causado: 5.
    * *API Resposta:* "A flecha ricocheteou no escudo."

4.  **Turno 2 (Player B):**
    * `SURRENDER`: (Player B percebe que esqueceu poções de vida).
    * *API Evento:* Player A recebe: `prompt: "Player B pede rendição. Aceitar? [S/N]"`


ter varios tipos de "pvp" no jogo, por exemplo, ao andar no mapa e ver um mob, posso:
- chamar ele para um pvp visual
- apenas fazer um "pvp" automatico, no qual meu personagem fica visualmente ao lado dele e
  executamos uma funcao de duelo basico e eu aguardo um tempo e depois sou "Liberado" do tempo de espera
  ou "morro" caso tenha perdido. essa funcao basica de duelo ja tem que fazer tudo desde contabilizar minha vida perdida
  ate meu xp ganho.


