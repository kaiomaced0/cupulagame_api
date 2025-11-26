
# Projeto Cúpula - Game Design Document (API-First)

**Visão Geral:** RPG "Headless" (baseado puramente em API) onde os jogadores interagem com o mundo via chamadas HTTP/Websockets. O front-end é desacoplado do back-end.
**Ambientação:** Um mundo confinado sob uma Cúpula, misturando elementos de sobrevivência, política e exploração.

---

## 1. Nomenclatura e Entidades Base

Definição dos nomes das classes e tabelas para manter consistência semântica no código.

### Seres Vivos
* **Nome da Classe:** `Creature` (ou `LivingEntity`)
* **Subclasses:** `Player`, `Monster`, `NPC`.
* **Dados:** HP, Posição (X, Y, Z), Atributos.

### Itens e Interativos
* **Nome da Classe:** `Item`
* **Diferenciação:** Uso de **Enums** (`ItemType`) para categorizar:
    * `WEAPON`, `ARMOR`, `CONSUMABLE`, `MATERIAL`.
* **Armazenamento:** `Container`
    * **Conceito:** O `Container` não é uma classe pai, mas um **Componente**.
    * *Exemplos:* Um `Player` tem um componente `inventory_container_id`. Um Baú (`Structure`) tem um `loot_container_id`.

### Criação (Crafting)
* **Nome da Classe:** `Station`
* **Conceito:** Mesas de trabalho definidas por tipo (`COOKING`, `SMITHING`, `ALCHEMY`).
* **Lógica:** O jogador interage com a `Station`, a API busca `Recipes` compatíveis com aquele tipo de estação.

---

## 2. Geografia e Estruturas

### Divisão de Mapa
* **Nível Macro:** `Island` (Ilha).
* **Subdivisão:** `Sector` (ou `Region`).
    * Define biomas e dificuldades.
    * Definido por coordenadas (Bounding Box: X_inicio, Y_inicio até X_fim, Y_fim).
    * Utilizado para lógica de *spawn* de monstros e clima.

### Construções (`Structure`)
Elementos colocados no mundo (paredes, casas, pontes).
* **Controle de Acesso (Fog of War / Roof Culling):**
    * **Lógica:** O `Player` possui um atributo `current_structure_id` (nulo se estiver ao ar livre).
    * **ItemStructure (Paredes/Pisos):** Possuem uma propriedade `layer`:
        * `EXTERIOR`: Visível de fora (Telhado).
        * `INTERIOR`: Visível apenas se `player.current_structure_id` corresponder à estrutura.
        * `BOTH`: Visível de ambos (Portas, Janelas).
    * **Segurança:** A API filtra o JSON de resposta. Se o player está fora, ele **não recebe** os dados do que está dentro (baús, players internos).

---

## 3. Visualização e Avatares (3D)

O Backend não envia arquivos `.obj` ou `.fbx`. O Backend envia **Metadados**.

* **Estratégia:** Composição por IDs e Morph Targets.
* **Payload da API:**
    ```json
    {
      "base_mesh": "HUMAN_MALE",
      "equipment": { "chest": "ARMOR_STEEL_ID_50" },
      "morphs": {
        "fat": 0.8,    // Slider de gordura
        "height": 1.1  // Slider de altura
      }
    }
    ```
* **Front-end:** Usa técnicas de *Anchors* (Pontos de ancoragem) ou *Blendshapes* para ajustar armaduras a corpos de tamanhos diferentes sem deformação visual excessiva.

---

## 4. Economia e Mercado

Cada Ilha possui seu próprio Marketplace.

### Estrutura de Vendas (`MarketListing`)
Sistema polimórfico que aceita produtos e serviços.
* **Tipo Produto:** Venda direta (Item X por Valor Y).
* **Tipo Serviço:** Prestação de serviço (tempo de execução + materiais).
    * *Exemplo:* "Faço Espada". Requer 2 Ferros. Demora 2 horas.
    * **Validação:** O comprador deve fornecer o Ouro + Materiais exigidos no JSON do serviço. O pagamento fica retido (*Escrow*) até a conclusão.

Criar uma economia "viva" é o que diferencia um RPG genérico de um mundo imersivo. No seu caso, a restrição de viagem (apenas fins de semana) é a **mecânica de ouro** para criar oportunidades de lucro para mercadores e escassez real.

Aqui está um modelo econômico estruturado em três pilares: **Disponibilidade Regional**, **Dinâmica de Mercado** e **Valor do Artesão**.

-----

### 4.1\. A Regra da Escassez Regional (Geografia Econômica)

Para que o preço varie organicamente, a API não deve definir preços fixos (ex: "Madeira custa 10"). A API deve definir um **Preço Base** e aplicar um **Multiplicador de Disponibilidade**.

#### A. Tags de Recursos nas Ilhas

Cada ilha no seu banco de dados deve ter uma lista de recursos naturais abundantes e escassos.

**Tabela `Island_Resources`:**

  * **Ilha do Norte:** `abundance: ["ICE", "FISH", "OIL"]`, `scarcity: ["WOOD", "FRUIT"]`
  * **Ilha da Floresta:** `abundance: ["WOOD", "HERBS", "FRUIT"]`, `scarcity: ["METAL", "OIL"]`

#### B. A Fórmula de Preço do NPC

O mercado de NPCs (o "piso" da economia) compra e vende baseando-se nessas tags.

$$PreçoLocal = PreçoBase \times Modificador$$

  * **Se Abundante:** Modificador = **0.5x** (Muito barato, bom para comprar).
  * **Se Normal:** Modificador = **1.0x**.
  * **Se Escasso:** Modificador = **2.0x** (Muito caro, bom para vender).

> **Gameplay de Fim de Semana:**
> Um jogador da Ilha da Floresta enche o inventário de Madeira (compra a 5g). No fim de semana, ele viaja para a Ilha do Norte. Lá, a madeira é escassa, então ele vende a 20g.
> **Risco:** Se ele morrer no caminho, perde tudo. Isso cria a profissão de "Caravaneiro" e "Escolta".

[Image of supply and demand economic graph showing price equilibrium]

-----

### 4.2\. Saturação de Mercado (Evitando Loop Infinito)

Se você deixar o preço fixo em 2.0x na Ilha do Norte, os jogadores vão "farmar" dinheiro infinitamente. Você precisa de um sistema de **Estoque Dinâmico (Market Pools)**.

Cada Ilha tem um "Pool" de demanda para cada recurso.

  * A Ilha do Norte precisa de 1.000 Madeiras por dia.
  * Conforme os jogadores vendem Madeira para o NPC da ilha, a "necessidade" cai.
  * Quando a necessidade cai, o preço de compra cai.

**Lógica da API:**

1.  Ilha precisa de Madeira. Preço de compra: **20g**.
2.  Jogador vende 500 madeiras.
3.  Estoque da Ilha encheu. Preço de compra cai para **12g**.
4.  Jogador vende mais 500.
5.  Ilha saturada. Preço de compra cai para **4g** (preço de lixo).

Isso obriga os jogadores a não venderem tudo de uma vez ou a procurarem outras ilhas.

-----

### 4.3\. A Economia de Serviços: O Valor do Mestre

Aqui entra a parte do jogador que dedicou tempo. Para que o serviço de um jogador valha mais que o de outro, precisamos de duas variáveis: **Nível de Proficiência (Mastery)** e **Qualidade do Resultado (Quality Grade)**.

#### A. Sistema de "Quality Grade" nos Itens

Um item no seu banco de dados não é apenas "Espada de Ferro". Ele é uma instância única.

```json
// Instância de Item Criado (ItemInstance)
{
  "id": 9999,
  "base_item_id": "IRON_SWORD",
  "crafter_id": "Player_Aragorn",
  "quality": 85.5, // 0 a 100
  "grade": "EPIC", // Calculado com base na quality
  "stats_modifier": 1.25 // 25% mais dano que a base
}
```

#### B. A Proficiência (Skill)

Cada vez que um jogador cria uma espada, ele ganha XP específico em `SMITHING`.

  * **Nível 1 (Aprendiz):** O RNG (Sorte) de qualidade varia entre 0 e 50. (Sempre sai item Lixo ou Comum).
  * **Nível 50 (Mestre):** O RNG varia entre 60 e 100. (Nunca sai item lixo, alta chance de Raro/Épico).

#### C. O Serviço no Marketplace

Quando o Mestre Ferreiro coloca seu serviço no mercado, a API mostra a estatística dele:

> **Anúncio:** "Forjo sua Espada de Ferro"
> **Artesão:** Player\_Aragorn (Nível 50 Smithing)
> **Previsão de Resultado:**
>
>   * Comum: 0%
>   * Raro: 40%
>   * Épico: 50%
>   * Lendário: 10%
>     **Preço:** 500 moedas.

> **Anúncio:** "Tento fazer uma espada"
> **Artesão:** Player\_Novato (Nível 5 Smithing)
> **Previsão:**
>
>   * Comum: 90%
>   * Raro: 10%
>     **Preço:** 10 moedas.

O jogador rico vai pagar 500 moedas para o Aragorn porque ele quer a chance do Lendário. O jogador pobre vai pagar 10 moedas para o Novato. **Isso cria um mercado real.**

-----

### 4.4\. Ideias Extras para a Cúpula

#### Imposto sobre Transações Estrangeiras (Tarifas)

O Ministro das Finanças da Ilha A pode ver que estão comprando muita madeira da Ilha B. Para proteger os lenhadores locais, ele aumenta o imposto de importação.

  * Se você comprar algo de um vendedor de outra ilha, o mercado cobra +30% de taxa.

#### Assinatura de Itens ("Crafted By")

Sempre salve o `crafter_name` no item. Em RPGs, itens feitos por jogadores famosos ganham valor de colecionador.

  * *Exemplo:* "Esta não é uma armadura qualquer, foi forjada pelo `LordVader` na Primeira Guerra dos Clãs." -\> Isso permite leilões de itens históricos.

#### Degradação e Reparo (Money Sink)

Para manter a economia dos artesãos girando, os itens precisam quebrar ou perder durabilidade máxima.

  * Uma espada dura 1000 hits.
  * Para consertar, você precisa contratar um serviço de Ferreiro.
  * Se o Ferreiro for ruim, ele conserta a espada mas reduz a durabilidade máxima dela permanentemente.
  * Se o Ferreiro for Mestre, ele conserta sem perdas. (Mais um motivo para pagar caro pelo serviço).

### Resumo da Lógica Econômica

1.  **Supply/Demand:** Biomas definem o preço base. Estoques dinâmicos impedem lucro infinito.
2.  **Logística:** O lucro real está no transporte (arriscado) entre ilhas nos fins de semana.
3.  **Valor do Artesão:** Nível alto = Melhores faixas de RNG na qualidade do item.
4.  **Confiança:** O sistema mostra as probabilidades do artesão antes de você contratar o serviço.


---
---
---


## 5. Sistema Político e Governamental

Estrutura hierárquica por Ilha.

### Cargos (`GovernmentOffice`)
Tabela flexível vinculando `Player` a `Role` na `Island`.
* **Cargos:** Presidente, Vice, Ministros (Finanças, Infraestrutura, Diplomacia).
* **Poderes:**
    * **Finanças:** Alterar alíquota de imposto mensal (`tax_value`), transferir verbas do Cofre da Ilha.
    * **Infraestrutura:** Criar Contratos Públicos.

### Clãs e Contratos Públicos
* **Clãs:** Podem cobrar impostos de membros. Possuem Cofre próprio.
* **Contratos (`PublicContract`):**
    * O Governo abre um contrato (ex: "Construir Ponte em X,Y").
    * O Clã aceita e executa.
    * Pagamento liberado do Cofre da Ilha para o Clã apenas mediante validação da conclusão (automática ou manual).

---

## 6. Regras de Gameplay e Restrições

### Ciclos e Votação
* **Eleições:** Ocorrem a cada 3 meses para definir/manter o governo da Ilha.

### Viagem e Imigração ("Estrangeiros")
* **Janela de Viagem:** Troca de ilhas permitida apenas nos fins de semana (janela de 50 horas).
* **Restrições ao Estrangeiro (Estar em uma ilha que não é sua residência):**
    * **Clã:** Não pode participar, missões de clã indisponíveis.
    * **Comércio:** Não pode vender no mercado (Compra liberada).
        * *Exceção:* Classe "Aventureiro" (regras especiais).
    * **Trabalho:** Apenas serviços básicos (coleta, transporte) permitidos.
* **Death Penalty (Morte fora de casa):**
    * Perde tudo o que carregava.
    * Respawn forçado na Ilha de origem (Nascimento/Residência).
    * Inventário "grande" só pode ser upado/gerido na ilha de residência.

---

## Próximos Passos Sugeridos
1.  Criar os diagramas ER (Entidade-Relacionamento) baseados nas tabelas acima.
2.  Definir os endpoints da API para o fluxo de **Login -> Seleção de Personagem -> Load do Mundo**.
3.  Implementar o protótipo da lógica de movimentação com verificação de colisão (`Structure`) no backend.

