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


---

## 6. A Arena de Combate (`PvpPadraoArena`)

O combate ocorre em uma arena tridimensional limitada, representada como um grid tático.

### Estrutura da Arena
* **Dimensões:** X, Y, Z (Long) - Define o tamanho da arena em "quadrados"
  * Exemplo: Arena 10x10x3 = 10 quadrados de largura, 10 de comprimento, 3 de altura
* **Estruturas:** Lista de `Structure` - Obstáculos, paredes, plataformas que podem bloquear movimento ou linha de visão
* **Coordenadas:** Cada posição é representada por (X, Y, Z)

**Inspiração de Design:** Sistema similar a Fire Emblem, Final Fantasy Tactics e Advance Wars, onde o posicionamento tático é crucial para a vitória.

---

## 7. Sistema de Turnos Simultâneos

**Diferente do sistema sequencial anterior**, agora o PVP funciona com **turnos simultâneos**:

1. **Seleção de Ações:** Cada participante escolhe **2 ações** em segredo
2. **Resolução Simultânea:** Ambos os participantes executam suas ações ao mesmo tempo
3. **Processamento:** A API resolve conflitos e calcula os resultados

### Tempo de Decisão
* Cada jogador tem **X segundos** (ex: 30s) para enviar suas 2 ações via `POST /combat/turn`
* *Timeout:* Se o jogador não responder, o personagem executa ação `WAIT` + `DEFEND` automaticamente

---

## 7. Sistema de Mobilidade e Peso

### Cálculo de Mobilidade

A distância que um personagem pode se mover por turno é baseada em uma **média de mobilidade**:

$$Mobilidade = \frac{Força + Velocidade}{PesoTotal}$$

Onde:
- **Força**: Atributo do personagem
- **Velocidade**: Atributo do personagem  
- **PesoTotal**: Peso do personagem + peso de todos os equipamentos/itens

### Categorias de Mobilidade

| Categoria | Condição | Movimento Base | Com Sorte (+15% chance) |
|-----------|----------|----------------|------------------------|
| 🐢 **Sobrecarregado** | ≤ 70% da média | 1 quadrado | 1-2 quadrados |
| 🚶 **Normal** | 70-130% da média | 2 quadrados | 2-3 quadrados |
| 🏃 **Ágil** | ≥ 130% da média | 2-3 quadrados* | 3-4 quadrados |

\* *Para personagens ágeis, o movimento varia aleatoriamente entre 2-3 quadrados a cada turno*

### Movimento Extra (Burst de Adrenalina)

Sempre existe uma chance de **15%** de conseguir mover +1 quadrado além do padrão, representando:
- Explosão de adrenalina
- Momento de sorte
- Terreno favorável

### Exemplos Práticos

**Guerreiro Pesado:**
```
Equipamento: Armadura de Placas (80kg) + Escudo (15kg) + Espada (8kg)
Peso Total: 103kg + 70kg (corpo) = 173kg
Força: 80
Velocidade: 40
Mobilidade: (80+40)/173 = 0.69 (69% - Sobrecarregado)
→ Move 1 quadrado/turno (raramente 2)
```

**Arqueiro Equilibrado:**
```
Equipamento: Armadura Leve (30kg) + Arco (3kg)
Peso Total: 33kg + 60kg (corpo) = 93kg
Força: 50
Velocidade: 90
Mobilidade: (50+90)/93 = 1.50 (150% - Normal/Ágil)
→ Move 2 quadrados/turno (às vezes 3)
```

**Assassino Leve:**
```
Equipamento: Roupa (5kg) + Adagas (2kg)
Peso Total: 7kg + 55kg (corpo) = 62kg
Força: 60
Velocidade: 120
Mobilidade: (60+120)/62 = 2.90 (290% - Ágil)
→ Move 2-3 quadrados/turno (raramente 4)
```

### Estratégia e Trade-offs

**Vantagens de Ser Ágil:**
- ✅ Maior controle de posicionamento
- ✅ Pode fugir de ataques melee
- ✅ Melhor para hit-and-run

**Vantagens de Ser Pesado:**
- ✅ Maior defesa/armadura
- ✅ Maior HP
- ✅ Não precisa se mover tanto (tanque)

---

## 8. Tipos de Ações

**REGRAS FUNDAMENTAIS:**
1. ✅ **Movimento varia por mobilidade** (Peso/Força/Velocidade): 1-3 quadrados
2. ✅ **Ataques são direcionais**: Aponta para quadrado, não para inimigo
3. ✅ **Defesas são direcionais**: Escolhe de qual direção defender
4. ✅ **Range do item define alcance**: `item.range` valida distância

Cada jogador pode combinar 2 ações por turno. As ações disponíveis são:

### A. Movimento (`MOVE`)
Move o personagem para um dos 8 quadrados adjacentes (ou verticalmente se puder voar).

**NOVA REGRA: Distância de Movimento Baseada em Peso/Força/Velocidade**

O sistema calcula uma **média de mobilidade** baseada em:
- Peso do personagem + equipamentos
- Atributo Força
- Atributo Velocidade

**Fórmula:** `MobilidadeMedia = (Força + Velocidade) / PesoTotal`

**Alcance de Movimento por Turno:**

| Mobilidade | Descrição | Quadrados Base | Quadrados com Sorte* |
|------------|-----------|----------------|---------------------|
| **≤ 70% da média** | Personagem Sobrecarregado | 1 quadrado | 1-2 quadrados |
| **70-130% da média** | Personagem Normal | 2 quadrados | 2-3 quadrados |
| **≥ 130% da média** | Personagem Ágil | 2-3 quadrados** | 3-4 quadrados |

\* **Chance de movimento extra:** Sempre existe uma pequena chance (ex: 15%) de o personagem conseguir se mover +1 quadrado além do padrão (burst de adrenalina).

\*\* Para personagens muito ágeis, o movimento varia entre 2-3 quadrados aleatoriamente.

**Exemplos:**

```
Guerreiro Pesado (Armadura Completa):
- Peso: 150kg, Força: 80, Velocidade: 40
- Mobilidade: Baixa (≤70%)
- Movimento: 1 quadrado/turno (raramente 2)

Arqueiro Leve:
- Peso: 60kg, Força: 50, Velocidade: 90
- Mobilidade: Normal
- Movimento: 2 quadrados/turno (raramente 3)

Assassino:
- Peso: 55kg, Força: 60, Velocidade: 120
- Mobilidade: Alta (≥130%)
- Movimento: 2-3 quadrados/turno (raramente 4)
```

**Movimento Horizontal (2D):**
* Posição atual: `(5, 5, 1)`
* **Se movimento = 1:** Pode mover para qualquer adjacente: `(4,4)`, `(4,5)`, `(4,6)`, `(5,4)`, `(5,6)`, `(6,4)`, `(6,5)`, `(6,6)`
* **Se movimento = 2:** Pode mover até 2 quadrados em linha reta ou diagonal
  * Exemplo: `(5,5) → (3,5)` ou `(5,5) → (3,3)`
* **Se movimento = 3:** Pode mover até 3 quadrados
  * Exemplo: `(5,5) → (2,5)` ou `(5,5) → (2,2)`

**Movimento Vertical (3D - Apenas se tiver habilidade de Voo):**
* Se o personagem pode voar, pode também mover para `Y=2` ou `Y=3`
* O custo de movimento vertical conta para o total
* Exemplo: De `(5, 5, 1)` pode ir para `(5, 5, 2)` (1 quadrado vertical) ou `(4, 5, 2)` (1 horizontal + 1 vertical = 2 quadrados)

**Payload:** 
```json
{
  "action": "MOVE",
  "targetPosition": { "x": 6, "y": 5, "z": 1 }
}
```

### B. Ataque (`ATTACK`)
Executa um ataque contra uma **posição específica** no grid. Suporta múltiplos tipos:

**NOVA REGRA: Ataques São Direcionados a Quadrados, Não a Alvos**

Diferente de sistemas tradicionais, o jogador **não aponta para o inimigo**, mas sim para **um quadrado específico** no grid. Se o inimigo estiver naquele quadrado quando o ataque for resolvido, ele será atingido.

**Por que isso importa?**
- Permite **esquiva por movimento** (inimigo sai do quadrado antes do ataque)
- Cria **tensão tática** (prever onde o inimigo estará)
- Ataques em área (AOE) podem atingir múltiplos quadrados
- Permite **feints** (atacar quadrado vazio para forçar movimento)

**Sistema de Alcance Baseado em `item.range`:**

O alcance do ataque é determinado pela propriedade `range` do item equipado:

| Tipo de Arma | Range | Descrição |
|--------------|-------|-----------|
| **Melee (Contato)** | 1 | Espadas, machados, socos - Apenas quadrados adjacentes |
| **Melee Longo** | 2 | Lanças, alabardas - Até 2 quadrados |
| **Ranged Curto** | 3-5 | Adagas arremessáveis, chakrams |
| **Ranged Médio** | 6-10 | Arcos, bestas |
| **Ranged Longo** | 11-15 | Arcos longos, rifles |
| **Mágico** | Variável | Magias podem ter ranges diferentes |

**Cálculo de Alcance:**

```java
// Posição do atacante
Position attackerPos = (5, 5, 1);

// Quadrado alvo escolhido
Position targetSquare = (8, 5, 1);

// Calcula distância
double distance = calculateDistance(attackerPos, targetSquare);
// distance = 3

// Valida range da arma
if (distance > weapon.getRange()) {
    return "Erro: Alvo fora de alcance!";
}

// Se válido, ataca o quadrado
// Se houver inimigo naquele quadrado → acerta
// Se quadrado vazio → ataque desperdiçado
```

* **Contato/Melee (range 1):** Espadas, machados, socos - Requer adjacência (1 quadrado)
* **Melee Longo (range 2):** Lanças - Alcance de 2 quadrados
* **Projétil/Ranged:** Arcos, bestas, armas de fogo - Usa `item.range` para validação
* **Mágico/Spell:** Magias, habilidades especiais - Range definido pela magia

**Validações:**
* Distância até o **quadrado alvo** (não o inimigo)
* Linha de visão (estruturas podem bloquear)
* Recursos necessários (munição, mana, stamina)
* Se o quadrado está dentro da arena

**Drop-off de Dano (Opcional):**
Armas ranged podem ter redução de dano por distância:
```
Dano Final = Dano Base × (1 - (distância - rangeÓtimo) × 0.05)
```

**Payload:**
```json
{
  "action": "ATTACK",
  "attackType": "MELEE|RANGED|MAGIC",
  "targetSquare": { "x": 8, "y": 5, "z": 1 },
  "weaponId": 42
}
```

**Exemplo de Combate:**
```
Turno atual:
- Jogador A está em (5, 5, 1)
- Jogador B está em (8, 5, 1)

Jogador A (com espada, range=1):
- Tenta atacar (8, 5, 1)
- Distância = 3
- 3 > 1 → ERRO: "Fora de alcance!"

Jogador A (com arco, range=10):
- Tenta atacar (8, 5, 1)
- Distância = 3
- 3 ≤ 10 → VÁLIDO!
- Ataque enviado para o quadrado (8, 5, 1)
- Se Jogador B ainda estiver lá na resolução → ACERTO!
- Se Jogador B moveu → MISS!
```

### C. Defesa (`DEFEND`)
Assume postura defensiva em um **quadrado específico**, aumentando resistência.

**NOVA REGRA: Defesa Direcionada a Quadrados**

Similar ao ataque, a defesa é direcionada a uma **posição específica** no grid, não ao inimigo. O jogador escolhe de qual direção espera o ataque.

**Como Funciona:**
- Jogador escolhe **um quadrado adjacente** de onde espera o ataque
- Se o ataque vier daquela direção → Bônus de defesa **maior** (+60%)
- Se o ataque vier de outra direção → Bônus de defesa **menor** (+30%)
- Se o ataque vier pelas costas (oposto à defesa) → Bônus **reduzido** (+15%)

**Efeitos Base:**
* +30-60% Defesa/Armadura durante este turno (depende da direção)
* +30% Chance de bloquear ataques críticos
* Reduz dano de todos os tipos de ataque

**Exemplos:**

```
Jogador está em (5, 5, 1)

Cenário 1: Defesa Bem Posicionada
- Escolhe defender do quadrado (6, 5, 1) [direita]
- Inimigo ataca de (6, 5, 1)
- Defesa: +60% ✅ "Você bloqueou o golpe com seu escudo!"

Cenário 2: Defesa Mal Posicionada
- Escolhe defender do quadrado (6, 5, 1) [direita]
- Inimigo ataca de (4, 5, 1) [esquerda - oposto]
- Defesa: +15% ❌ "Você foi pego desprevenido!"

Cenário 3: Defesa Parcial
- Escolhe defender do quadrado (6, 5, 1) [direita]
- Inimigo ataca de (5, 6, 1) [frente]
- Defesa: +30% ⚠️ "Você se defendeu parcialmente"
```

**Direções de Defesa:**
```
    (4,4)  (5,4)  (6,4)
      ↖     ↑     ↗
    (4,5)  [5,5]  (6,5)
      ←    PLAYER   →
    (4,6)  (5,6)  (6,6)
      ↙     ↓     ↘
```

**Payload:**
```json
{
  "action": "DEFEND",
  "defendDirection": { "x": 6, "y": 5, "z": 1 }
}
```

### D. Esperar (`WAIT`)
O personagem permanece em posição, observando.

**Efeitos:**
* Não consome Stamina
* +10% Esquiva para este turno
* Pode ser útil para estratégias de conservação de recursos

**Payload:**
```json
{
  "action": "WAIT"
}
```

### E. Esquivar (`DODGE`)
Foca totalmente em evitar ataques.

**Efeitos:**
* +70% Esquiva para este turno
* Não pode atacar neste turno
* Consome Stamina moderada
* Ideal quando se espera um ataque pesado

**Payload:**
```json
{
  "action": "DODGE"
}
```

### F. Usar Item (`USE_ITEM`)
Consome um item do inventário.

**Exemplos:**
* Poção de Vida
* Poção de Mana
* Buff temporário
* Granada/Item ofensivo

**Limitações:**
* Só pode usar itens marcados como "usável em combate"
* Alguns itens têm cooldown

**Payload:**
```json
{
  "action": "USE_ITEM",
  "itemId": 42
}
```

---

## 9. Sistema de Alcance e Direcionamento

### Alcance de Armas (`item.range`)

Toda arma/habilidade tem uma propriedade `range` que determina quantos quadrados de distância ela pode atingir:

| Range | Tipo de Arma | Exemplos |
|-------|--------------|----------|
| 1 | Melee Curto | Espada, Machado, Soco, Adaga |
| 2 | Melee Longo | Lança, Alabarda, Mangual |
| 3-5 | Ranged Curto | Adagas Arremessáveis, Chakrams |
| 6-10 | Ranged Médio | Arco, Besta, Funda |
| 11-15 | Ranged Longo | Arco Longo, Rifle |
| Variável | Mágico | Depende da magia |

### Cálculo de Distância para Ataque

```java
// Posição do atacante
Position attacker = (5, 5, 1);

// Quadrado alvo (não o inimigo!)
Position targetSquare = (8, 5, 1);

// Distância Manhattan (grid)
int distance = Math.abs(8-5) + Math.abs(5-5) = 3

// Ou Euclidiana (mais precisa)
double distance = Math.sqrt((8-5)² + (5-5)²) = 3.0

// Valida com range da arma
if (distance <= weapon.range) {
    // Ataque válido
}
```

### Direcionamento Posicional

**Conceito Chave:** Jogadores não "miram" em inimigos, mas em **coordenadas do grid**.

**Para Ataques:**
```json
{
  "action": "ATTACK",
  "targetSquare": { "x": 8, "y": 5, "z": 1 }
}
```
- Se houver inimigo naquele quadrado → ACERTO
- Se quadrado vazio → MISS (ataque desperdiçado)
- Permite **mind games** e predição

**Para Defesas:**
```json
{
  "action": "DEFEND",
  "defendDirection": { "x": 6, "y": 5, "z": 1 }
}
```
- Escolhe de qual quadrado adjacente espera o ataque
- Direção correta → +60% defesa
- Direção errada → +15% defesa

---

## 10. Mecânica de Colisão e Combate Forçado

**Quando dois participantes tentam ocupar o mesmo quadrado:**

### Cenário de Colisão
Se no **primeiro** ou **segundo** movimento de um turno ambos os combatentes ocuparem o mesmo quadrado:

1. **Interrupção:** Os próximos movimentos planejados são cancelados
2. **Combate Forçado:** Uma função especial de combate é acionada
3. **Resolução:** 
   * Ambos executam seus ataques simultaneamente
   * Quem causar **mais dano** permanece no quadrado
   * Quem causar **menos dano** é empurrado de volta

### Cálculo de Recuo
O perdedor do embate é empurrado na **direção oposta** de onde veio:

**Exemplo:**
* Player A estava em `(3, 5, 1)` e moveu para `(5, 5, 1)`
* Player B estava em `(7, 5, 1)` e moveu para `(5, 5, 1)`
* **Colisão em** `(5, 5, 1)`!
* Combate executado: A causou 45 de dano, B causou 30 de dano
* **Resultado:** 
  * Player A permanece em `(5, 5, 1)`
  * Player B é empurrado de volta para `(7, 5, 1)` ou `(6, 5, 1)`

**Payload de Resposta:**
```json
{
  "turnResult": "COLLISION_COMBAT",
  "winner": "PlayerA",
  "damageDealt": {
    "PlayerA": 45,
    "PlayerB": 30
  },
  "positions": {
    "PlayerA": { "x": 5, "y": 5, "z": 1 },
    "PlayerB": { "x": 7, "y": 5, "z": 1 }
  },
  "message": "Vocês colidiram! PlayerA dominou o embate e empurrou PlayerB para trás!"
}
```

---

## 11. Tipos de Modos de PVP

O jogo oferece diferentes modos de combate para diferentes situações:

### A. PVP Tático (Visual)
* **Descrição:** Combate completo em grid, todos os turnos visíveis
* **Uso:** Duelos formais, arenas, combates importantes
* **Características:**
  * Interface visual do grid
  * Tempo para planejamento tático
  * Replay disponível após o combate

### B. PVP Automático Rápido
* **Descrição:** Sistema simplificado para encontros casuais
* **Uso:** Encontros com mobs comuns durante exploração
* **Características:**
  * Personagem fica "travado" visualmente ao lado do mob
  * Função de duelo básico executada automaticamente
  * Aguarda X segundos e libera o personagem
  * Contabiliza automaticamente: HP perdido, XP ganho, Loot
  * **Resultado:** "Vitória" (liberado) ou "Derrota" (morte/desmaiado)

**Exemplo de Fluxo Automático:**
```json
POST /combat/quick-duel
{
  "targetMobId": 123
}

Response:
{
  "status": "IN_PROGRESS",
  "estimatedTime": 15,
  "message": "Você está em combate com Goblin Guerreiro..."
}

// Após 15 segundos...
{
  "status": "VICTORY",
  "hpLost": 25,
  "xpGained": 150,
  "loot": [
    { "itemId": 5, "name": "Espada Enferrujada" }
  ]
}
```

---

## 12. Exemplo Completo de Turno

**Cenário:** Player A (Mago) vs Mob B (Orc Guerreiro) em Arena 10x10x3

**Posições Iniciais:**
* Player A: `(2, 2, 1)`
* Mob B: `(8, 8, 1)`

### Turno 1

**Player A envia:**
```json
{
  "actions": [
    {
      "action": "MOVE",
      "targetPosition": { "x": 3, "y": 3, "z": 1 }
    },
    {
      "action": "ATTACK",
      "attackType": "MAGIC",
      "targetId": 99
    }
  ]
}
```

**Mob B (IA) decide:**
```json
{
  "actions": [
    {
      "action": "MOVE",
      "targetPosition": { "x": 7, "y": 7, "z": 1 }
    },
    {
      "action": "MOVE",
      "targetPosition": { "x": 6, "y": 6, "z": 1 }
    }
  ]
}
```

**Resolução:**
* Movimento 1: A vai para `(3,3,1)`, B vai para `(7,7,1)` - OK
* Movimento 2: A ataca de `(3,3,1)`, B move para `(6,6,1)` - OK
* Distância: ~4.24 quadrados
* Ataque mágico de A: **Acerto!** 35 de dano
* Nenhuma colisão ocorreu

**Resposta da API:**
```json
{
  "turnNumber": 1,
  "results": {
    "PlayerA": {
      "position": { "x": 3, "y": 3, "z": 1 },
      "actions": ["MOVE executado", "ATTACK acertou! 35 de dano"],
      "hp": 100,
      "stamina": 85
    },
    "MobB": {
      "position": { "x": 6, "y": 6, "z": 1 },
      "actions": ["MOVE executado", "MOVE executado"],
      "hp": 65,
      "stamina": 70
    }
  },
  "events": [
    "PlayerA moveu para (3, 3, 1)",
    "MobB avançou para (7, 7, 1)",
    "PlayerA conjurou Bola de Fogo!",
    "MobB avançou para (6, 6, 1) e foi atingido! -35 HP"
  ]
}
```

---

## 13. Sistema de Iniciação de Combate

Para informações detalhadas sobre como os combates são iniciados, consulte o documento [pvp_iniciacao.md](./pvp_iniciacao.md).

### Resumo dos Tipos de Iniciação:

1. **Convite para Duelo** (`DUEL_INVITE`)
   - Requer aceitação do oponente
   - Não requer contato físico para convidar
   - Timeout de 2 minutos

2. **Ataque Direto** (`DIRECT_ATTACK`)
   - **Requer ENCOSTAR no alvo** (distance ≤ 1.5)
   - Combate forçado, sem opção de recusa
   - Usado para ataques hostis

3. **Ataque Furtivo** (`STEALTH_ATTACK`)
   - Player deve estar em modo `stealthMode`
   - **Requer ENCOSTAR no alvo**
   - Dá vantagem: ação especial "Backstab" no 1º turno

4. **Desafio ao Mob** (`MOB_DUEL`)
   - Mob decide baseado em `duelAcceptChance` (0-100)
   - Não requer contato físico
   - Mobs podem recusar baseado em personalidade

5. **Ataque ao Mob** (`MOB_ATTACK`)
   - **Requer ENCOSTAR no mob**
   - Combate forçado
   - Pode ser furtivo ou direto

### Validação de Proximidade (Touch)

Para qualquer ataque (player ou mob), o atacante **DEVE ENCOSTAR** no alvo:

```
Distância válida: distance ≤ 1.5
- Mesma posição: OK ✅
- Adjacente (4 direções): OK ✅
- Diagonal: OK ✅
- 2+ quadrados: ERRO ❌
```

---

## 14. Considerações Técnicas

### Validações da API
* Verificar se as posições de destino são válidas (dentro da arena)
* Verificar se há estruturas bloqueando o caminho
* Validar alcance de ataques baseado na posição
* Detectar colisões em ambos os movimentos do turno
* Prevenir movimento através de obstáculos sólidos

### Performance
* Arenas limitadas (máximo 20x20x5) para manter processamento rápido
* Cache de cálculos de distância e linha de visão
* Timeout rigoroso para prevenir travamento de sessões

### Balanceamento
* Duas ações por turno permite combinações estratégicas:
  * MOVE + MOVE (reposicionamento rápido)
  * MOVE + ATTACK (aproximar e atacar)
  * DEFEND + WAIT (postura ultra-defensiva)
  * DODGE + USE_ITEM (esquivar e curar)
* Colisões incentivam planejamento cuidadoso de rotas

---

## 15. Balanceamento de Combate

### Sistema de HP Recomendado

Para um sistema equilibrado onde táticas importam mais que spam de ataques:

**HP Base por Nível:**
```
Nível 1:   150-200 HP
Nível 10:  250-350 HP  
Nível 20:  400-600 HP
Nível 50:  800-1200 HP
```

**Justificativa (com dano de 10-50 por ataque):**

Com **200 HP iniciais**:
- Morre em **4-20 golpes** (dependendo da arma)
- Combate dura **6-15 turnos** (tempo para táticas)
- Poções de cura (50-100 HP) fazem sentido
- Críticos (2x dano) são impactantes mas não instant-kill
- Backstab furtivo (+50% dano) é recompensador mas não OP
- Permite uso estratégico de DEFEND, DODGE, USE_ITEM

**Exemplo de Combate Balanceado:**
```
HP Inicial: 200 HP cada

Turno 1:
- A: MOVE + ATTACK (30) → B: 170/200 HP
- B: DODGE + ATTACK (miss + 25) → A: 175/200 HP

Turno 2:
- A: ATTACK + ATTACK (40 + 35) → B: 95/200 HP
- B: DEFEND + USE_ITEM (+50 HP) → B: 145/200 HP

Turno 3-5: Combate tático continua...
```

### Progressão de Armas

| Tier | Dano | Range | Exemplos |
|------|------|-------|----------|
| Básico | 10-20 | 1 | Espada Enferrujada |
| Comum | 20-35 | 1-2 | Espada de Ferro |
| Raro | 35-50 | 1-2 | Espada de Aço |
| Épico | 50-80 | 2-3 | Lâmina Élfica |
| Lendário | 80-120 | 2-5 | Espada Dracônica |

### Sistema de Armadura

Armadura reduz dano direto:
```
Dano Final = Dano Bruto - (Armadura × PenetraçãoArma)

Exemplo:
- Ataque: 50 dano
- Armadura: 30
- Penetração: 0.5
- Dano Final = 50 - (30 × 0.5) = 35
```

**Trade-off de Armaduras:**
| Tipo | Defesa | Peso | Mobilidade |
|------|--------|------|------------|
| Leve | 10-20 | 20-40kg | Alta (2-3 quadrados) |
| Média | 30-50 | 50-80kg | Normal (2 quadrados) |
| Pesada | 60-100 | 90-120kg | Baixa (1 quadrado) |

### Balanceamento de Mobilidade

**Build Ágil (Assassino):**
- HP: 180
- Dano: 30-45
- Movimento: 3 quadrados
- Estratégia: Hit-and-run, posicionamento

**Build Equilibrado (Guerreiro):**
- HP: 250
- Dano: 40-55
- Movimento: 2 quadrados
- Estratégia: Pressão constante, versatilidade

**Build Tanque (Paladino):**
- HP: 350
- Dano: 25-40
- Movimento: 1 quadrado
- Estratégia: Controle de área, absorção de dano

---

## 16. Resumo de Regras Críticas

✅ **Movimento varia por Peso/Força/Velocidade:**
- Sobrecarregado (≤70%): 1 quadrado
- Normal (70-130%): 2 quadrados  
- Ágil (≥130%): 2-3 quadrados
- Sempre 15% chance de +1 quadrado extra

✅ **Ataques são direcionais:**
- Aponta para **coordenada** no grid, não para inimigo
- Usa `item.range` para validar alcance
- Se inimigo estiver lá → ACERTO
- Se quadrado vazio → MISS

✅ **Defesas são direcionais:**
- Escolhe **quadrado adjacente** de onde espera ataque
- Direção correta → +60% defesa
- Direção lateral → +30% defesa
- Direção errada (costas) → +15% defesa

✅ **Range define alcance:**
- Melee: 1-2 quadrados
- Ranged: 3-15 quadrados
- Validação: `distância <= weapon.range`

✅ **HP Recomendado:** 200 base (combates de 6-15 turnos)

✅ **Colisão:** Ocupar mesmo quadrado → Combate forçado + empurrão

