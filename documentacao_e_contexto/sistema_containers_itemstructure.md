# Sistema de Containers e StructureUnit

## Visão Geral

O sistema de containers permite que contêineres (inventários, baús, mochilas) tenham posição 3D no mundo, visuais variados através de StructureUnit, e possam ser vinculados a diferentes contextos (player, mob, estrutura, ou soltos no mapa).

## Tipos de Container (ContainerTipo)

| ID | Tipo | Descrição | Uso |
|----|------|-----------|-----|
| 0 | INVENTARIO | Inventário principal | Player |
| 1 | BAU | Baú tradicional | Mundo, Structure, Mob |
| 2 | MOCHILA | Mochila extra | Player (futuro) |

## Entidades

### Container (base)

Classe base para todos os tipos de armazenamento.

**Campos:**
- `containerTipo`: Tipo do container (enum)
- `capacidadeMaximaEspaco`: Espaço máximo (slots)
- `pesoMaximo`: Peso máximo suportado
- `posicaoX, posicaoY, posicaoZ`: Posição 3D no mundo
- `structureUnit`: Visual do container (ManyToOne) - unidade visual 3D
- `mob`: Mob dono (opcional, ManyToOne)
- `structure`: Estrutura onde está (opcional, ManyToOne)

**Métodos:**
```java
void setPosicao(Long x, Long y, Long z)  // Helper para definir posição
```

### Inventario (extends Container)

Inventário específico de player.

**Campos adicionais:**
- `player`: Player dono (OneToOne)

### BaseContainerStructureUnit

Configuração de visual e limites para containers (antes chamado BaseContainerItemStructure).

**Campos:**
- `nome`: Nome do template (ex: "Baú de Madeira Pequeno")
- `descricao`: Descrição do visual
- `containerTipo`: Tipo de container ao qual se aplica
- `structureUnit`: Visual 3D do container (StructureUnit)
- `capacidadeMaximaEspacoLimite`: Limite de espaço permitido
- `pesoMaximoLimite`: Limite de peso permitido
- `possibilidade`: Peso para seleção aleatória
- `ativo`: Se está ativo

**Conceito de Limites:**

Os limites NÃO são os valores que o container terá, mas sim o **máximo permitido**.

Exemplo:
```
BaseContainerStructureUnit: "Baú de Madeira Pequeno"
- capacidadeMaximaEspacoLimite: 200
- pesoMaximoLimite: 500

Ao criar um container:
- Pode ter capacidade de 150 (ok, menor que 200)
- Pode ter capacidade de 200 (ok, igual ao limite)
- NÃO pode ter capacidade de 250 (excede limite)
```

## ContainerService

### Métodos Principais

#### `criarContainer(ContainerTipo tipo, Long capacidade, Long peso)`

Cria um container genérico com visual aleatório.

**Fluxo:**
1. Sorteia um BaseContainerStructureUnit do tipo especificado
2. Valida capacidade e peso contra os limites da base
3. Ajusta valores se excederem os limites
4. Cria container com o StructureUnit (visual) da base

#### `criarInventario(Player player, Long capacidadeCalculada, Long pesoCalculado)`

Cria um inventário para player com visual aleatório.

**Diferença do `criarContainer`:**
- Retorna `Inventario` (não `Container`)
- Associa automaticamente ao player
- Usa ContainerTipo.INVENTARIO

**Validação de Limites:**
```java
// Player tem atributos que resultam em:
capacidadeCalculada = 300 slots
pesoCalculado = 600 unidades

// Base sorteada tem:
capacidadeMaximaEspacoLimite = 250
pesoMaximoLimite = 500

// Container criado terá:
capacidade = 250 (limitado pela base)
peso = 500 (limitado pela base)
```

#### `criarBauNoMundo(Long x, Long y, Long z, Long capacidade, Long peso)`

Cria um baú em posição específica no mundo.

**Uso:**
```java
Container bau = containerService.criarBauNoMundo(100L, 50L, 200L, 150L, 300L);
// Baú criado em (100, 50, 200) com capacidade 150 e peso máximo 300
```

## Integração com PlayerService

### Criação de Inventário

Ao criar um player:

```java
// 1. Calcula capacidade baseada em atributos (armadura, força, tamanho, raça)
CapacidadeInventario capacidade = calcularCapacidadeInventario(player);
// Retorna: capacidadeEspaco=300, pesoMaximo=700

// 2. ContainerService cria inventário com visual aleatório
Inventario inventario = containerService.criarInventario(
    player, 
    capacidade.getCapacidadeEspaco(),  // 300
    capacidade.getPesoMaximo()          // 700
);

// 3. Se a base sorteada tiver limites menores, ajusta automaticamente
// Exemplo: base tem limite de 250 espaço e 500 peso
// Resultado: inventario com 250 espaço e 500 peso

// 4. Visual do inventário vem do ItemStructure da base sorteada
```

### Classe Auxiliar: CapacidadeInventario

Retorna os valores calculados (não cria o container):

```java
public static class CapacidadeInventario {
    private final Long capacidadeEspaco;
    private final Long pesoMaximo;
    
    // getters...
}
```

## Seleção de Visual (StructureUnit)

### Randomização Ponderada

Funciona igual aos sistemas de combate e estruturas:

**Exemplo - Configurações de INVENTARIO:**

```
ID: 1
Nome: "Mochila de Couro Comum"
containerTipo: INVENTARIO
capacidadeLimite: 200
pesoLimite: 400
possibilidade: 70
ativo: true

ID: 2
Nome: "Mochila Reforçada"
containerTipo: INVENTARIO
capacidadeLimite: 300
pesoLimite: 600
possibilidade: 25
ativo: true

ID: 3
Nome: "Mochila Élfica Mágica"
containerTipo: INVENTARIO
capacidadeLimite: 400
pesoLimite: 800
possibilidade: 5
ativo: true
```

**Probabilidades:**
- Mochila Comum: 70% de chance (capacidade até 200, peso até 400)
- Mochila Reforçada: 25% de chance (capacidade até 300, peso até 600)
- Mochila Mágica: 5% de chance (capacidade até 400, peso até 800)

### Visual x Capacidade

**Importante:** O visual (StructureUnit) define os **limites**, não os valores finais.

**Cenário 1 - Gigante com Mochila Comum:**
```
Player Gigante:
- Capacidade calculada: 400 slots
- Peso calculado: 900

Mochila Comum sorteada:
- Limite espaço: 200
- Limite peso: 400

Resultado:
- Inventário tem 200 slots (limitado pela mochila)
- Inventário tem 400 peso (limitado pela mochila)
- Visual: Mochila de Couro Comum
```

**Cenário 2 - Goblin com Mochila Mágica:**
```
Player Goblin:
- Capacidade calculada: 120 slots
- Peso calculado: 300

Mochila Mágica sorteada:
- Limite espaço: 400
- Limite peso: 800

Resultado:
- Inventário tem 120 slots (capacidade do goblin)
- Inventário tem 300 peso (capacidade do goblin)
- Visual: Mochila Élfica Mágica
```

## Contextos de Container

### 1. Container de Player (Inventário)

```java
Inventario inventario = ...;
inventario.setPlayer(player);
// Sem posição (viaja com o player)
// Sem structure ou mob
```

### 2. Container Solto no Mundo (Baú no mapa)

```java
Container bau = containerService.criarBauNoMundo(100L, 50L, 200L, 150L, 300L);
// Tem posição (100, 50, 200)
// Sem player, mob ou structure
// Qualquer um pode acessar
```

### 3. Container em Structure (Baú em casa/loja)

```java
Container bauCasa = containerService.criarContainer(ContainerTipo.BAU, 200L, 400L);
bauCasa.setStructure(minhaHomePlayer);
bauCasa.setPosicao(10L, 0L, 5L);  // Posição relativa dentro da structure
// Pertence à structure
// Acesso controlado pelo dono da structure
```

### 4. Container em Mob (futuro)

```java
Container lootMob = containerService.criarContainer(ContainerTipo.BAU, 50L, 100L);
lootMob.setMob(mobBoss);
// Sem posição (viaja com o mob)
// Acesso ao matar o mob
```

## StructureUnit (Visual)

### Composição

Um StructureUnit representa o modelo 3D do container:

```
StructureUnit: "Baú de Madeira"
├── StructureUnitPart 1: Tampa (madeira marrom)
│   ├── inicioX: 0, fimX: 10
│   ├── inicioY: 8, fimY: 10
│   ├── material: MADEIRA
│   └── color: MARROM
├── StructureUnitPart 2: Corpo (madeira marrom)
│   ├── inicioX: 0, fimX: 10
│   ├── inicioY: 0, fimY: 8
│   ├── material: MADEIRA
│   └── color: MARROM
└── StructureUnitPart 3: Fechadura (metal dourado)
    ├── inicioX: 5, fimX: 6
    ├── inicioY: 5, fimY: 6
    ├── material: METAL
    └── color: DOURADO
```

### Layer

StructureUnit tem um `layer` que define a camada de renderização:
- Permite sobreposição de objetos
- Define ordem de desenho

### Tipo

StructureUnit tem um `tipo` (StructureUnitTipo) que categoriza:
- CONTAINER
- FURNITURE
- DECORATION
- WALL
- FLOOR
- etc.

## BaseContainerStructureUnitRepository

### Métodos

#### `findByTipo(ContainerTipo tipo)`

Busca configurações base ativas para um tipo específico.

**Exemplo:**
```java
List<BaseContainerStructureUnit> basesBau = repository.findByTipo(ContainerTipo.BAU);
// Retorna todas as configurações de BAU ativas
```

## Fluxo Completo: Criar Player com Inventário Visual

```
1. PlayerService.criarPlayer()
   ↓
2. Sorteia raça: ELFO
   ↓
3. Aplica atributos visuais e de combate
   armadura: 25.0
   ad (força): 35.0
   tamanho médio: 80
   ↓
4. Calcula capacidade
   capacidade = 100 + (80 × 0.8 × 0.5) = 132 slots
   peso = 50 + (25×10) + (35×5) + (80×0.8×0.3) = 494
   ↓
5. ContainerService.criarInventario(player, 132, 494)
   ↓
6. Sorteia BaseContainerStructureUnit (INVENTARIO)
   - 70% chance: "Mochila Comum" (limite: 200, 400)
   - 25% chance: "Mochila Reforçada" (limite: 300, 600)
   - 5% chance: "Mochila Mágica" (limite: 400, 800)
   ↓
7. Sorteou: "Mochila Reforçada"
   ↓
8. Valida limites
   132 <= 300 ✓ (ok)
   494 <= 600 ✓ (ok)
   ↓
9. Cria Inventario
   - capacidade: 132 (do cálculo)
   - peso: 494 (do cálculo)
   - structureUnit: "Mochila Reforçada" (StructureUnit #15)
   - player: Elfo
   ↓
10. Retorna Inventario com visual de "Mochila Reforçada"
```

## Exemplos de Configuração (Seeds)

### Inventários

```java
// Mochila Comum (70% chance)
BaseContainerStructureUnit mochilaComum = new BaseContainerStructureUnit();
mochilaComum.setNome("Mochila de Couro Comum");
mochilaComum.setDescricao("Mochila básica de couro marrom");
mochilaComum.setContainerTipo(ContainerTipo.INVENTARIO);
mochilaComum.setStructureUnit(structureUnitMochilaCouro);
mochilaComum.setCapacidadeMaximaEspacoLimite(200L);
mochilaComum.setPesoMaximoLimite(400L);
mochilaComum.setPossibilidade(70L);
mochilaComum.setAtivo(true);

// Mochila Reforçada (25% chance)
BaseContainerStructureUnit mochilaReforcada = new BaseContainerStructureUnit();
mochilaReforcada.setNome("Mochila Reforçada");
mochilaReforcada.setDescricao("Mochila com reforços metálicos");
mochilaReforcada.setContainerTipo(ContainerTipo.INVENTARIO);
mochilaReforcada.setStructureUnit(structureUnitMochilaReforcada);
mochilaReforcada.setCapacidadeMaximaEspacoLimite(300L);
mochilaReforcada.setPesoMaximoLimite(600L);
mochilaReforcada.setPossibilidade(25L);
mochilaReforcada.setAtivo(true);

// Mochila Mágica (5% chance)
BaseContainerStructureUnit mochilaMagica = new BaseContainerStructureUnit();
mochilaMagica.setNome("Mochila Élfica Mágica");
mochilaMagica.setDescricao("Mochila encantada com espaço expandido");
mochilaMagica.setContainerTipo(ContainerTipo.INVENTARIO);
mochilaMagica.setStructureUnit(structureUnitMochilaMagica);
mochilaMagica.setCapacidadeMaximaEspacoLimite(400L);
mochilaMagica.setPesoMaximoLimite(800L);
mochilaMagica.setPossibilidade(5L);
mochilaMagica.setAtivo(true);
```

### Baús

```java
// Baú de Madeira Pequeno
BaseContainerStructureUnit bauMadeiraPequeno = new BaseContainerStructureUnit();
bauMadeiraPequeno.setNome("Baú de Madeira Pequeno");
bauMadeiraPequeno.setDescricao("Baú rústico de madeira");
bauMadeiraPequeno.setContainerTipo(ContainerTipo.BAU);
bauMadeiraPequeno.setStructureUnit(structureUnitBauMadeiraPequeno);
bauMadeiraPequeno.setCapacidadeMaximaEspacoLimite(100L);
bauMadeiraPequeno.setPesoMaximoLimite(200L);
bauMadeiraPequeno.setPossibilidade(60L);
bauMadeiraPequeno.setAtivo(true);

// Baú de Ferro Médio
BaseContainerStructureUnit bauFerroMedio = new BaseContainerStructureUnit();
bauFerroMedio.setNome("Baú de Ferro Médio");
bauFerroMedio.setDescricao("Baú reforçado com ferro");
bauFerroMedio.setContainerTipo(ContainerTipo.BAU);
bauFerroMedio.setStructureUnit(structureUnitBauFerroMedio);
bauFerroMedio.setCapacidadeMaximaEspacoLimite(250L);
bauFerroMedio.setPesoMaximoLimite(500L);
bauFerroMedio.setPossibilidade(30L);
bauFerroMedio.setAtivo(true);

// Baú do Tesouro Grande
BaseContainerStructureUnit bauTesouro = new BaseContainerStructureUnit();
bauTesouro.setNome("Baú do Tesouro Grande");
bauTesouro.setDescricao("Baú ornamentado para guardar tesouros");
bauTesouro.setContainerTipo(ContainerTipo.BAU);
bauTesouro.setStructureUnit(structureUnitBauTesouro);
bauTesouro.setCapacidadeMaximaEspacoLimite(500L);
bauTesouro.setPesoMaximoLimite(1000L);
bauTesouro.setPossibilidade(10L);
bauTesouro.setAtivo(true);
```

## Converter HTTP

### ContainerTipoConverter

Permite usar IDs numéricos na API:

```
GET /containers?tipo=0  → ContainerTipo.INVENTARIO
POST /containers { "tipo": 1 }  → ContainerTipo.BAU
```

## Arquivos Criados/Modificados

### Novos Arquivos

1. `BaseContainerItemStructure.java` - Configuração de visual e limites
2. `BaseContainerItemStructureRepository.java` - Repository com query por tipo
3. `ContainerService.java` - Service com lógica de criação de containers
4. `ContainerTipoConverter.java` - Converter HTTP para enum

### Arquivos Modificados

1. `Container.java` - Adicionado posição 3D, ItemStructure, Mob, Structure
2. `ContainerTipo.java` - Adicionado fromId() e getId()
3. `PlayerService.java` - 
   - Criada classe auxiliar CapacidadeInventario
   - Modificado calcularCapacidadeInventario para retornar valores
   - Modificado criarPlayer para usar ContainerService
   - Removido criação manual de Inventario
4. `EnumConverterProvider.java` - Registrado ContainerTipoConverter

## Vantagens do Sistema

### 1. Variedade Visual

Mesmo com capacidades similares, players podem ter diferentes visuais de inventário.

### 2. Limites Realistas

Um goblin pequeno não pode ter uma mochila gigante, mesmo que seus atributos permitam.

### 3. Raridade

Mochilas mágicas são raras (5%), criando sensação de sorte ao conseguir uma.

### 4. Flexibilidade

Sistema suporta containers em múltiplos contextos (player, mob, structure, mundo).

### 5. Extensibilidade

Fácil adicionar novos tipos de container ou novos visuais.

## Comportamento de Limites

### Player Forte com Mochila Fraca

```
Gigante (forte):
- Cálculo: 400 espaço, 900 peso

Sorteia: Mochila Comum
- Limite: 200 espaço, 400 peso

Resultado: 200 espaço, 400 peso
Mensagem: "Sua mochila limita sua capacidade de carga!"
```

### Player Fraco com Mochila Forte

```
Goblin (fraco):
- Cálculo: 120 espaço, 300 peso

Sorteia: Mochila Mágica
- Limite: 400 espaço, 800 peso

Resultado: 120 espaço, 300 peso
Mensagem: "Você não consegue aproveitar toda a capacidade desta mochila!"
```

### Player e Mochila Equilibrados

```
Humano (balanceado):
- Cálculo: 150 espaço, 605 peso

Sorteia: Mochila Comum
- Limite: 200 espaço, 400 peso

Resultado: 150 espaço, 400 peso
Espaço: ok (dentro do limite)
Peso: limitado pela mochila
```

## Conclusão

O sistema de containers com StructureUnit cria um mundo dinâmico onde:
- Players têm inventários com visuais variados
- Baús podem ser colocados em qualquer lugar do mundo
- Estruturas podem ter múltiplos containers (baús, estantes, etc)
- Mobs podem dropar loot de seus containers
- Limites realistas baseados no visual do container (StructureUnit)
- Sistema de raridade adiciona emoção à criação de personagens
- StructureUnit pode ser usado independentemente (com usuário) ou como visual de container
- Import/Export JSON permite clonar visuais de containers
