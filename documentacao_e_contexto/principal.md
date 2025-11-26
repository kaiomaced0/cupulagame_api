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
