# Sistema de Estruturas (Structures)

## Visão Geral

O sistema de estruturas gerencia construções e locais especiais no jogo. Cada player recebe automaticamente uma HOME_PLAYER ao ser criado, baseada em sua raça.

## Tipos de Estrutura (StructureTipo)

| ID | Tipo | Descrição |
|----|------|-----------|
| 0 | CONSTRUCAO | Construção genérica |
| 1 | CENARIO | Elemento de cenário |
| 2 | ILHA_ACESSO | Portal de acesso a ilha |
| 3 | HOME_PLAYER | Casa do player (única por player) |
| 4 | LOJA_ILHA | Loja da ilha |
| 5 | LOJA_EMPRESA | Loja de empresa |
| 6 | EMPRESA | Sede de empresa |
| 7 | GUILDA | Sede de guilda |
| 8 | SALA_RESTRITA | Sala com acesso restrito |
| 9 | PRISAO | Prisão |

## Entidades

### Structure

Representa uma estrutura no mundo do jogo.

**Campos:**
- `itemStructures`: Lista de itens que compõem a estrutura
- `containers`: Lista de containers (baús, etc)
- `tipo`: Tipo da estrutura (enum StructureTipo)
- `ilha`: Ilha onde está localizada
- `usuario`: Dono da estrutura
- `arenaPvp`: Arena PvP associada (se aplicável)

### BaseStructureTipo

Configuração de estrutura padrão por raça e tipo.

**Campos:**
- `structureTipo`: Tipo de estrutura (HOME_PLAYER, LOJA, etc)
- `playerRaca`: Raça específica (ELFO, HUMANO, etc)
- `structureTemplate`: Estrutura que serve como template
- `nome`: Nome descritivo do template
- `descricao`: Descrição do template
- `possibilidade`: Peso para seleção aleatória
- `ativo`: Se está ativo para uso

**Exemplo:**
```
ID: 1
Nome: "Casa Élfica Básica"
Raça: ELFO
Tipo: HOME_PLAYER
Possibilidade: 70
Ativo: true
```

## StructureService

### Métodos Principais

#### `criarHomePlayer(Player player)`

Cria uma HOME_PLAYER para o player baseado na raça.

**Validações:**
- ✅ Player não pode ser null
- ✅ Player deve ter um Usuario associado
- ✅ Player deve ter uma raça definida
- ✅ Player não pode ter HOME_PLAYER já criada

**Fluxo:**
1. Valida precondições
2. Verifica se já tem HOME_PLAYER (lança exceção se sim)
3. Sorteia uma configuração base da raça
4. Cria cópia da estrutura template
5. Associa ao usuario do player
6. Persiste no banco

**Seleção Ponderada:**
```java
// Se existem 3 templates para ELFO:
// - Casa Élfica Básica: possibilidade 70
// - Casa Élfica Luxo: possibilidade 20
// - Casa Élfica Rústica: possibilidade 10
// Total: 100

// Probabilidades:
// Básica: 70% de chance
// Luxo: 20% de chance
// Rústica: 10% de chance
```

#### `playerTemHomePlayer(Player player)`

Verifica se o player já possui uma HOME_PLAYER.

**Retorno:** `true` se já possui, `false` caso contrário

#### `listarStructuresDoUsuario(Usuario usuario)`

Lista todas as estruturas de um usuário.

#### `listarStructuresDoUsuarioPorTipo(Usuario usuario, StructureTipo tipo)`

Lista estruturas de um tipo específico de um usuário.

## Integração com PlayerService

### Criação Automática de HOME_PLAYER

Ao criar um player (`PlayerService.criarPlayer`):

```java
// 1. Cria posição e status
// 2. Sorteia raça
// 3. Define atributos visuais
// 4. Aplica atributos de combate
// 5. Cria inventário
// 6. Persiste player
// 7. Cria HOME_PLAYER ← AUTOMÁTICO
```

**Ordem de Criação:**
1. PlayerPosicao
2. PlayerStatus
3. Player (com atributos)
4. Inventario (capacidade calculada)
5. Structure HOME_PLAYER (baseada na raça)

## Multiplicadores Raciais (Futuro)

Cada raça pode ter diferentes estilos de HOME_PLAYER:

### ELFO
- Casa Élfica na Árvore (possibilidade: 50)
- Casa Élfica de Pedra (possibilidade: 30)
- Cabana Élfica (possibilidade: 20)

### HUMANO
- Casa de Madeira (possibilidade: 40)
- Casa de Pedra (possibilidade: 40)
- Cabana Simples (possibilidade: 20)

### ANAO
- Forja com Habitação (possibilidade: 60)
- Casa Subterrânea (possibilidade: 40)

### ORC
- Cabana de Guerra (possibilidade: 50)
- Acampamento Fortificado (possibilidade: 50)

### GOBLIN
- Caverna Improvisada (possibilidade: 70)
- Toca no Solo (possibilidade: 30)

### GIGANTE
- Casa Grande de Pedra (possibilidade: 80)
- Cabana Reforçada (possibilidade: 20)

## Regras de Negócio

### HOME_PLAYER

1. **Única por Player**: Cada player só pode ter uma HOME_PLAYER
2. **Criação Automática**: Criada automaticamente ao criar player
3. **Baseada na Raça**: Template selecionado de acordo com a raça
4. **Seleção Aleatória**: Se múltiplos templates disponíveis, sorteia ponderadamente
5. **Associação ao Usuario**: Estrutura pertence ao Usuario, não ao Player

### Validações

- ❌ Não é possível criar segunda HOME_PLAYER para mesmo player
- ❌ Não é possível criar HOME_PLAYER sem raça definida
- ❌ Não é possível criar HOME_PLAYER sem usuario associado
- ⚠️ Se não houver template configurado para a raça, lança exceção

## BaseStructureTipoRepository

### Métodos

#### `findByRacaAndTipo(PlayerRaca raca, StructureTipo tipo)`

Busca configurações base ativas para uma raça e tipo específicos.

**Exemplo:**
```java
List<BaseStructureTipo> templates = repository.findByRacaAndTipo(
    PlayerRaca.ELFO, 
    StructureTipo.HOME_PLAYER
);
// Retorna todos os templates de HOME_PLAYER para ELFO que estão ativos
```

## Fluxo de Dados

### Criação de Player com HOME_PLAYER

```
Usuario (já existe)
    ↓
Player.criarPlayer()
    ↓
1. Define raça: ELFO
2. Aplica atributos
3. Cria inventário
4. Persiste player
    ↓
StructureService.criarHomePlayer(player)
    ↓
1. Valida se não tem HOME_PLAYER
2. Busca templates: findByRacaAndTipo(ELFO, HOME_PLAYER)
3. Sorteia template (ponderado por possibilidade)
4. Copia estrutura do template
5. Associa ao player.usuario
6. Persiste structure
    ↓
HOME_PLAYER criada
```

## Converter HTTP

### StructureTipoConverter

Permite enviar IDs numéricos na API em vez de strings.

**Mapeamento:**
```
GET /structures?tipo=3  → StructureTipo.HOME_PLAYER
POST /structures { "tipo": 6 }  → StructureTipo.EMPRESA
```

**Enum ID:**
```java
StructureTipo.HOME_PLAYER.getId()  // retorna 3
StructureTipo.fromId(3)  // retorna HOME_PLAYER
```

## Cópia de Estrutura

### Método `copiarStructure(Structure template)`

**Atualmente:**
- Copia apenas o tipo da estrutura
- ItemStructures e Containers NÃO são copiados

**Futuro (Implementar):**
- Cópia profunda de ItemStructures
- Cópia profunda de Containers
- Cópia de ItemStructureParts
- Ajuste de posições relativas

## Exemplos de Uso

### Criar Player (automático)

```java
PlayerResponse response = playerService.criarPlayer(usuario, request);
// HOME_PLAYER é criada automaticamente
```

### Verificar se tem HOME_PLAYER

```java
boolean temHome = structureService.playerTemHomePlayer(player);
if (temHome) {
    System.out.println("Player já possui casa");
}
```

### Listar Estruturas do Usuário

```java
List<Structure> structures = structureService.listarStructuresDoUsuario(usuario);
structures.forEach(s -> {
    System.out.println("Tipo: " + s.getTipo());
    System.out.println("Ilha: " + s.getIlha().getNome());
});
```

### Listar apenas HOME_PLAYER

```java
List<Structure> homes = structureService.listarStructuresDoUsuarioPorTipo(
    usuario, 
    StructureTipo.HOME_PLAYER
);
// Deve retornar apenas 1 elemento
```

## Arquivos Criados/Modificados

### Novos Arquivos

1. `BaseStructureTipo.java` - Entidade de configuração base
2. `BaseStructureTipoRepository.java` - Repository com query customizada
3. `StructureService.java` - Service com lógica de negócio
4. `StructureTipoConverter.java` - Converter para HTTP

### Arquivos Modificados

1. `StructureTipo.java` - Adicionado `fromId()` e `getId()`
2. `PlayerService.java` - Adicionado injeção de StructureService e criação automática de HOME_PLAYER
3. `EnumConverterProvider.java` - Registrado StructureTipoConverter

## Seeds Necessários (Futuro)

Para funcionamento completo, criar seeds:

```sql
-- Exemplo para ELFO HOME_PLAYER
INSERT INTO base_structure_tipo 
    (structure_tipo, player_raca, nome, descricao, possibilidade, ativo, structure_template_id)
VALUES
    ('HOME_PLAYER', 'ELFO', 'Casa Élfica Básica', 'Casa simples na copa das árvores', 70, true, 1),
    ('HOME_PLAYER', 'ELFO', 'Casa Élfica Luxo', 'Casa elegante com decoração refinada', 20, true, 2),
    ('HOME_PLAYER', 'ELFO', 'Cabana Élfica', 'Cabana rústica no solo', 10, true, 3);

-- Repetir para cada raça
```

## Conclusão

O sistema de estruturas permite que cada player tenha uma casa única baseada em sua raça, com variedade através da seleção aleatória ponderada. A arquitetura está preparada para expansão com novos tipos de estruturas (lojas, empresas, guildas) seguindo o mesmo padrão.
