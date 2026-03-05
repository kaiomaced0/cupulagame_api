# Sistema de Inventário e Capacidade Dinâmica

## Visão Geral

O sistema de inventário conecta cada Player a um único Inventario através de uma relação OneToOne. A capacidade do inventário é calculada dinamicamente baseada nos atributos de combate do player.

## Relacionamento

### Player → Inventario (OneToOne)

```java
@OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
private Inventario inventario;
```

- **Cascata**: Ao deletar um Player, o Inventario é deletado automaticamente
- **Orphan Removal**: Garante que inventários órfãos sejam removidos
- **Bidirecional**: Player conhece Inventario e vice-versa

### Inventario → Player (OneToOne)

```java
@OneToOne
@JoinColumn(name = "player_id")
private Player player;
```

## Cálculo de Capacidade

### Fórmula de Capacidade Máxima de Espaço (slots)

```
capacidadeEspaco = 100 + (tamanhoMedio × multiplicadorRaca × 0.5)
```

- **Base**: 100 slots
- **Tamanho Médio**: `(tamanhoX + tamanhoY + tamanhoZ) / 3`
- **Multiplicador Racial**: Varia por raça

### Fórmula de Peso Máximo

```
pesoMaximo = 50 + (armadura × 10) + (ad × 5) + (tamanhoMedio × multiplicadorRaca × 0.3)
```

- **Base**: 50 unidades de peso
- **Resistência (armadura)**: Cada ponto de armadura adiciona 10 unidades
- **Força (ad)**: Cada ponto de Attack Damage adiciona 5 unidades
- **Tamanho Racial**: Contribui proporcionalmente ao tamanho e raça

## Multiplicadores Raciais

| Raça    | Multiplicador | Descrição                          |
|---------|---------------|------------------------------------|
| GIGANTE | 2.0           | Dobro de capacidade                |
| ORC     | 1.5           | 50% a mais de capacidade           |
| HUMANO  | 1.0           | Capacidade padrão (referência)     |
| ANAO    | 0.9           | 10% a menos de capacidade          |
| ELFO    | 0.8           | 20% a menos de capacidade          |
| GOBLIN  | 0.6           | 40% a menos de capacidade          |

## Atributos Utilizados

### Do Player

- **armadura** (Double): Representa a resistência física, aumenta peso máximo
- **ad** (Double): Attack Damage, representa força, aumenta peso máximo
- **tamanhoX, tamanhoY, tamanhoZ** (Long): Dimensões físicas do player
- **raca** (PlayerRaca): Define o multiplicador de capacidade

## Fluxo de Criação

### 1. Criação do Player (`criarPlayer`)

```java
// 1. Cria posição e status
// 2. Sorteia raça
// 3. Define tamanho baseado na raça
// 4. Sorteia visual (cabelo, pele, orelha)
// 5. Aplica atributos de combate (armadura, ad, etc.)
// 6. Calcula e cria inventário ← NOVO
// 7. Persiste player com inventário
```

### 2. Cálculo Automático do Inventário

```java
Inventario inventario = calcularCapacidadeInventario(player);
inventarioRepository.persist(inventario);
player.setInventario(inventario);
```

## Exemplos de Capacidade

### Exemplo 1: Gigante Tanque

- **Raça**: GIGANTE (mult: 2.0)
- **Armadura**: 50.0
- **AD**: 30.0
- **Tamanho Médio**: 200 (grande)

```
capacidadeEspaco = 100 + (200 × 2.0 × 0.5) = 100 + 200 = 300 slots
pesoMaximo = 50 + (50 × 10) + (30 × 5) + (200 × 2.0 × 0.3)
           = 50 + 500 + 150 + 120 = 820 unidades
```

### Exemplo 2: Elfo Ágil

- **Raça**: ELFO (mult: 0.8)
- **Armadura**: 20.0
- **AD**: 40.0
- **Tamanho Médio**: 80 (pequeno)

```
capacidadeEspaco = 100 + (80 × 0.8 × 0.5) = 100 + 32 = 132 slots
pesoMaximo = 50 + (20 × 10) + (40 × 5) + (80 × 0.8 × 0.3)
           = 50 + 200 + 200 + 19.2 = 469 unidades
```

### Exemplo 3: Humano Balanceado

- **Raça**: HUMANO (mult: 1.0)
- **Armadura**: 35.0
- **AD**: 35.0
- **Tamanho Médio**: 100 (médio)

```
capacidadeEspaco = 100 + (100 × 1.0 × 0.5) = 100 + 50 = 150 slots
pesoMaximo = 50 + (35 × 10) + (35 × 5) + (100 × 1.0 × 0.3)
           = 50 + 350 + 175 + 30 = 605 unidades
```

## Método Público

### `calcularCapacidadeInventario(Player player)`

- **Acesso**: public (pode ser usado externamente)
- **Retorno**: Inventario com capacidades calculadas
- **Uso**: 
  - Criação automática de inventário na criação do player
  - Recálculo ao subir de nível (futuro)
  - Recálculo ao equipar itens que alteram atributos (futuro)

## Validações

O método `calcularCapacidadeInventario` valida que o player possui:

- ✅ armadura definida
- ✅ ad definido
- ✅ tamanhoX, tamanhoY, tamanhoZ definidos
- ✅ raca definida

Caso algum atributo esteja nulo, lança `IllegalStateException`.

## Futuras Melhorias

1. **Recálculo ao Subir de Nível**: Chamar `calcularCapacidadeInventario` quando player sobe de nível
2. **Recálculo ao Equipar Itens**: Itens que aumentam força/resistência devem atualizar capacidade
3. **Capacidade Ocupada**: Adicionar campos para rastrear espaço/peso atualmente ocupados
4. **Validação de Adição**: Verificar se há espaço/peso disponível antes de adicionar itens
5. **Efeitos Temporários**: Poções/buffs que aumentam capacidade temporariamente
6. **Bonificações de Guilda**: Guildas podem dar bônus de capacidade

## Arquivos Modificados

### Entidades
- `Player.java`: Adicionado campo `inventario` (OneToOne)
- `Inventario.java`: Adicionado campo `player` (OneToOne)

### Services
- `PlayerService.java`: 
  - Injetado `InventarioRepository`
  - Adicionado `getMultiplicadorRaca(PlayerRaca)`
  - Adicionado `calcularCapacidadeInventario(Player)`
  - Modificado `criarPlayer()` para criar inventário automaticamente

### Imports Adicionados
- `org.cupula.model.containers.Inventario`
- `org.cupula.model.containers.enums.ContainerTipo`
- `org.cupula.repository.containers.InventarioRepository`
- `jakarta.persistence.CascadeType`

## Comportamento no Banco

### Criação de Player

1. `PlayerPosicao` é persistida
2. `PlayerStatus` é persistida (referencia posição)
3. `Inventario` é persistida (sem player ainda)
4. `Player` é persistida (referencia status e inventário)
5. Relacionamento bidirecional estabelecido

### Deleção de Player

1. Player é deletado
2. Inventario é deletado automaticamente (CascadeType.ALL)
3. Status e posição também são deletados por cascade (se configurado)

## Conclusão

O sistema de inventário dinâmico garante que cada player tenha uma capacidade única baseada em seus atributos, criando diferenciação entre raças e builds. Gigantes tanques carregam muito mais que elfos ágeis, mantendo o balanceamento do jogo.
