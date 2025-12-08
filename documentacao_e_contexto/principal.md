# 📘 **Game Design Document — Regras e Estrutura Lógica do Jogo**

Este documento descreve todas as regras, estruturas e comportamentos principais do jogo.
Ele serve como guia para implementação em qualquer tecnologia (Unreal, Unity, Quarkus API, etc.).

---

# 📂 **1. Modelos (Entidades do Jogo)**

## 🧍 **1.1 Player**

O Player é o personagem controlado pelo jogador.

### **Atributos Base**

* **vida**: 100
* **regenVida**: 1 por segundo
* **armadura**: 0
* **velocidade**: 50
* **forca**: 5
* **forcaPernas**: 5
* **critico**: 50 (chance ou multiplicador, dependendo do sistema)

### **Atributos Derivados**

* **danoBase** = forca
* **danoComArma** = arma.forcaReal
* **velocidadeDeCorrida** = velocidade + (forcaPernas * multiplicador)
* **possuiArma**: boolean
* **armaEquipada**: Tool | null

### **Ações**

* **mover(x, y)**
* **interagir(objeto)**
* **equipar(arma)**
* **desequipar()**
* **atacar(alvo)**

---

## 🪓 **1.2 Classe Base de Ferramentas — `Tool`**

É a classe mãe de todos os itens que podem ser usados como arma ou ferramenta.

### **Atributos**

* nome
* tipo (espada, machado, picareta, etc.)
* forcaReal
* critico
* durabilidade (100 inicial)
* peso

### **Métodos**

* getDano()
* reduzirDurabilidade()
* podeQuebrar()

---

## ⚔️ **1.3 Espada (filha de Tool)**

### **Atributos Específicos**

* tipoCorte: perfurante/cortante
* velocidadeGolpe: rápida

---

## 🪓 **1.4 Machado (filha de Tool)**

### **Atributos Específicos**

* tipoCorte: pesado
* velocidadeGolpe: lenta
* bônus: dano extra em madeira

---

## 🟥 **1.5 Entidade Interagível — `ObjetoMundo`**

Base de qualquer objetos físicos no mundo (pedra, árvore, baú, inimigo, etc.).

### **Atributos**

* id
* nome
* modelo3d
* vida
* resistencia
* dropList

### **Métodos**

* receberDano()
* destruir()
* gerarDrops()

---

## 👹 **1.6 Ente de combate — `NPC`**

Pode ser usado para inimigos, animais, criaturas.

### **Atributos**

* vida
* dano
* agressividade
* velocidade

### **Métodos**

* atacar(player)
* fugir()
* perseguir(player)

---

# ⚙️ **2. Mecânicas Gerais**

---

# 🥊 **2.1 Sistema de Dano**

### **Regras**

1. Se o player estiver sem arma:
   **danoTotal = forca do player**
2. Se o player estiver com arma:
   **danoTotal = arma.forcaReal**
3. Crítico
   Se `Random() <= critico%` →
   **danoTotal *= 2**
4. Armadura reduz dano recebido
   **danoRecebido = danoTotal - armadura**

---

# ❤️ **2.2 Regeneração de Vida**

A cada X segundos:

```
vida = min(vidaMax, vida + regenVida)
```

---

# 🧱 **2.3 Sistema de Durabilidade das Ferramentas**

Sempre que atacar:

```
durabilidade -= 1
se durabilidade == 0 → ferramenta quebrada
```

Se quebrar, o player volta a atacar com dano base.

---

# 🎒 **2.4 Sistema de Inventário**

* slots limitados (por ex. 20)
* armas ocupam 1 slot
* recursos ocupam 1 slot cada stack

---

# 🤝 **2.5 Sistema de Interação**

Um objeto pode ser interagível se:

* O player estiver dentro da área de interação
* O objeto tiver `interagivel = true`

Exemplos:

* pegar arma
* abrir baú
* cortar árvore
* atacar inimigo

---

# 🗡️ **2.6 Equipar Armas**

Regra:

```
player.equipar(arma):
    player.armaEquipada = arma
```

Desequipar:

```
player.armaEquipada = null
```

---

# 🧭 **2.7 Spawns e Start do Player**

No início do jogo:

* Player nasce na posição definida pelo GameMode
* Inventário vazio
* Atributos iniciais carregados
* Nenhuma arma equipada

---

# 🌍 **3. Biomas e Recursos**

### **Floresta**

* madeira
* frutos
* ervas

### **Deserto**

* areia
* argila
* minerais raros

### **Montanhas**

* ferro
* pedra
* monstros fortes

### **Campos**

* linho
* trigo
* animais domesticáveis

---

# ⚒️ **4. Criação de Itens (Crafting)**

### Exemplos:

* **Linho → tecido → roupas leves**
* **Madeira + Pedra → machado**
* **Metal + forja → espada premium**

Regras de crafting:

1. Player precisa dos materiais
2. Player precisa da estação correta (forja, bancada, tear…)
3. Item é criado com durabilidade 100

---

# ⚔️ **5. Combate**

### Regras do combate corpo a corpo:

* Player usa dano baseado na arma equipada
* Inimigos têm retângulo de colisão
* Se player estiver no range, pode atacar
* Se inimigo tiver agressividade alta, persegue o player

### Cooldown

Armas diferentes têm tempos de ataque diferentes:

* Espada: rápida
* Machado: lenta, porém mais dano

---

# 🧠 **6. Hierarquia de Classes (Resumo)**

```
Tool (classe base)
 ├── Espada
 └── Machado

ObjetoMundo (classe base)
 ├── NPC
 ├── Árvore
 ├── Pedra
 └── ItemSolto (representação física da Tool no mapa)

Player
 └── pode interagir com ObjetoMundo
```

---

# 🧩 **7. Objetos físicos no mapa**

Para cada arma ou item que aparece no mundo:

* existe um “ItemSolto”
* ele contém a referência do item de verdade (ex: a classe `Espada`)

Quando o player coleta:

* o ItemSolto some do mundo
* a instância da arma entra no inventário

---

# 🏰 **8. Sistema de Guildas**

A guilda é uma organização de jogadores com base física e progressão estrutural.

## 📡 **8.1 Torre de Comunicação**

A estrutura central para a influência da guilda. Sua evolução desbloqueia mecânicas críticas:

*   **Nível 1:** Chat local e missões básicas da região.
*   **Nível 2:** **Alerta de Invasão** (Radar que notifica membros sobre inimigos no território).
*   **Nível 3:** Inteligência de Mercado e Contratos Globais.
*   **Nível 4 (Máximo):** **Linha Direta** (Diplomacia instantânea) e Habilidade de Expansão Inter-Ilhas.

## 🌿 **8.2 Expansão e Filiais**

Ao atingir o nível máximo da Torre de Comunicação, a guilda ganha o direito de se expandir para uma **Nova Ilha**.

*   **Matriz e Filial:** A guilda original torna-se a Matriz. A nova base na outra ilha é a Filial.
*   **Progressão:** A Filial inicia do zero (nível 1), independente do nível da Matriz.
*   **Economia Compartilhada:**
    *   Matriz e Filial podem trocar valores monetários mensalmente.
    *   A Matriz pode definir um "Salário" ou subsídio para ajudar a Filial a crescer.
*   **Acesso por Patente:** Membros com patente específica (ex: Oficiais ou Veteranos) podem utilizar a Filial como "Base Externa" (ponto de respawn, acesso a baús), facilitando operações em múltiplas ilhas.

---

# 🏢 **9. Sistema de Empresas**

Empresas funcionam com lógica similar às guildas, mas focadas puramente no comércio e lucro.

*   **Pontos de Venda:** Uma empresa pode abrir múltiplas lojas/quiosques na mesma ilha para aumentar alcance.
*   **Expansão Internacional:**
    *   Custo elevado (Taxa de Expansão) para abrir uma sede em outra ilha.
    *   **Custo de Manutenção (Impostos):** Manter operações em duas ou mais ilhas gera um custo recorrente alto. Se a empresa não pagar os impostos de operação internacional, a filial é fechada/lacrada.

---

# 🆔 **10. Cidadania, Identidade e Leis**

O sistema legal define o que um jogador pode ou não fazer em cada ilha, baseado em seu status de residência.

## 📜 **10.1 Documentação e Status**

*   **Residente:** Cidadão oficial da ilha. Pode comprar imóveis, votar, e paga impostos normais.
*   **Visitante (Com Visto):** Jogador de outra ilha com permissão temporária.
    *   Possui **Limite de Estadia** (tempo real ou dias de jogo).
    *   Paga taxas extras em lojas e serviços.
    *   Restrições de compra (não pode comprar terrenos).
*   **Indigente (Ilegal):** Jogador sem documentos ou com visto expirado.

## ⚖️ **10.2 Gestão e Punições**

*   **Painel do Presidente:** O Presidente da ilha possui um painel de gerenciamento onde define:
    *   Valor dos impostos para residentes vs. estrangeiros.
    *   Tempo limite de vistos de turismo.
    *   Visualização da lista de ilegais na ilha.
*   **Lista de Caçadas:** Jogadores em status de **Indigente** entram automaticamente para uma lista de procurados.
    *   Outros jogadores (Caçadores de Recompensa) ou a polícia NPC podem caçá-los.
    *   A punição pode envolver prisão, deportação forçada ou multas pesadas.

---

# 🌱 **11. Sistema de Plantações**

O sistema de agricultura permite o cultivo de plantas que crescem em estágios e podem ser colhidas.

## 🌿 **11.1 Estrutura da Planta**

*   **Base Mob:** Plantas possuem "Vida" (HP) e podem ser destruídas/atacadas, mas são imóveis.
*   **Níveis de Crescimento:** Cada planta possui múltiplos estágios (ex: Semente -> Broto -> Planta Jovem -> Planta Adulta -> Com Frutos).
*   **Visual:** Cada estágio possui uma `StructureBase` associada, permitindo variação visual e de cor.

## 🚜 **11.2 Mecânica de Cultivo**

*   **Plantio:** Requer sementes e solo adequado.
*   **Crescimento:**
    *   O tempo de crescimento é influenciado pela **Raça** do plantador e suas **Proficiências** em natureza.
    *   Certas raças (ex: Elfos, Dryads) podem ter bônus de velocidade de crescimento.
*   **Colheita/Destruição:**
    *   Ao ser "destruída" (HP zerado ou ação de colher), a planta dropa itens.
    *   **Drops e XP:** A quantidade e qualidade dos drops e XP variam conforme a proficiência de quem colhe.

---

# 🌍 **12. Geração de Mundo e Biomas (Cenários)**

O mundo é dividido em Cenários (Biomas) que ditam as regras de spawn e comportamento ambiental.

## 🌲 **12.1 Configuração de Spawn**

Cada Cenário possui uma lista de **Regras de Spawn** (`SpawnRule`) que definem:
*   **O que spawna:** Qual Mob ou Planta.
*   **Densidade:** Quantidade máxima por área (chunk/setor).
*   **Tempo de Respawn:** Intervalo para reaparecer após morte/colheita.
*   **Condições de Proximidade:**
    *   Ex: "Plantas X nascem perto de grama alta".
    *   Ex: "Mobs Y nascem em áreas de areia".

## ☀️ **13. Mecânica de Solos Vivos (Grama)**

Solos reagem ao ambiente, criando um mundo dinâmico.

*   **Exposição Solar:** Blocos de "Terra" expostos ao sol (sem blocos acima) acumulam "Energia Solar".
*   **Crescimento de Grama:**
    *   Após X tempo de exposição, a "Terra" se transforma em "Terra com Grama".
    *   Se um bloco for colocado em cima da grama (bloqueando o sol), ela morre e volta a ser "Terra".
*   **Propagação:** A grama pode se espalhar para blocos de terra adjacentes se houver luz suficiente.

---

# 🐾 **14. Mecânica de Animais e Pragas**

O comportamento dos animais vai além de simples spawns; eles possuem ciclos de vida, necessidades e impacto ecológico.

## 🍖 **14.1 Alimentação e Reprodução**

Animais possuem um instinto de sobrevivência e perpetuação da espécie ligado à alimentação.

*   **Gatilho de Reprodução:** Se um animal estiver se alimentando (seja pastando, caçando ou sendo alimentado por um jogador), ele entra em estado fértil.
*   **Ciclo de Reprodução:**
    *   Após se alimentar, inicia-se um contador (cooldown) para reprodução.
    *   Se houver outro animal da mesma espécie próximo e também fértil, eles geram um filhote.
    *   O tempo de gestação e o intervalo entre reproduções variam conforme a espécie (ex: coelhos reproduzem rápido, elefantes demoram).

## 🦗 **14.2 Sistema de Pragas e Superpopulação**

O equilíbrio ecológico depende do cenário e da presença de predadores ou controle populacional.

*   **Definição de Praga:** Se a população de uma espécie em uma determinada área (ilha ou chunk) ultrapassar um limite crítico (densidade excessiva), ela é considerada uma "Praga".
*   **Consequências da Praga:**
    *   **Destruição de Recursos:** Pragas consomem recursos (plantas, colheitas) muito mais rápido.
    *   **Doenças:** Alta densidade pode gerar debuffs de doença que se espalham para outros mobs ou players.
    *   **Agressividade:** Animais pacíficos podem se tornar agressivos devido à falta de comida.
*   **Variação por Cenário:**
    *   Certas espécies prosperam mais em biomas específicos (ex: Insetos em pântanos podem virar praga rapidamente; Coelhos em planícies também).
    *   A ausência de predadores naturais em uma ilha facilita o surgimento de pragas.

## ⏳ **14.3 Ciclo de Vida (Idade)**

Mobs não são estáticos; eles envelhecem, e isso altera seus atributos e comportamento.

*   **Estágios de Vida:**
    1.  **Filhote:** Fraco, rápido, foge de tudo. Não dropa recursos úteis.
    2.  **Jovem:** Começa a desenvolver comportamento padrão.
    3.  **Adulto:** Status base completo. Capaz de reproduzir.
    4.  **Ancião:** Mais lento, porém pode ser mais forte (experiente) ou ter drops mais raros (couro rígido, presas maiores).
*   **Variação de Atributos:**
    *   A "Idade" atua como um multiplicador de atributos (Força, Velocidade, HP).
    *   Comportamento muda com a idade (ex: um Lobo Jovem é imprudente e ataca sozinho; um Lobo Adulto ataca em matilha; um Ancião pode liderar a matilha).



