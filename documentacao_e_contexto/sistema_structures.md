# Sistema de Estruturas (Structures)

## Visão Geral

O sistema de estruturas gerencia construções e locais especiais no jogo. Suporta criação manual de structures pelo usuário, import/export JSON para clonar estruturas visualmente, e sistema de favoritas. Cada player também recebe automaticamente uma HOME_PLAYER ao ser criado, baseada em sua raça.

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
- `nome`: Nome da estrutura (nullable)
- `structureUnits`: Lista de unidades visuais que compõem a estrutura
- `containers`: Lista de containers (baús, etc) com visual (StructureUnit)
- `tipo`: Tipo da estrutura (enum StructureTipo) - pode ser null
- `ilha`: Ilha onde está localizada (opcional)
- `usuario`: Dono da estrutura (obrigatório)
- `arenaPvp`: Arena PvP associada (opcional)

### StructureUnit

Unidade visual/componente de uma estrutura (antes chamado ItemStructure).

**Campos:**
- `nome`: Nome da unidade (nullable)
- `parts`: Lista de partes (StructureUnitPart) que compõem a unidade
- `layer`: Camada de renderização (FOREGROUND, BACKGROUND, etc)
- `tipo`: Tipo da unidade (StructureUnitTipo)
- `usuario`: Dono da unidade (obrigatório) - permite StructureUnit independente

### StructureUnitPart

Parte individual de uma unidade com coordenadas 3D.

**Campos:**
- `nome`: Nome da parte (nullable)
- `inicioX, inicioY, inicioZ`: Coordenadas de início
- `fimX, fimY, fimZ`: Coordenadas de fim
- `material`: Material da parte (ManyToOne)
- `color`: Cor/Material colorido (ManyToOne ColorMaterial)
- `areaContato`: Se é área de contato/interação

### BaseStructure

Configuração de estrutura padrão por raça e tipo (antes chamado BaseStructureTipo).

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

## CRUD de Structure (Manual pelo Usuário)

### Endpoints Disponíveis

#### Criar Structure
**POST** `/structures`
```json
{
  "nome": "Minha Casa Customizada",
  "tipo": "CONSTRUCAO"  // opcional, pode ser null
}
```
- Structure é vinculada automaticamente ao usuário logado
- Campos `ilha`, `arenaPvp` não são definidos (uso manual)
- `tipo` pode ser null

#### Listar Structures do Usuário
**GET** `/structures`

Retorna todas as structures do usuário com hierarquia completa:
- Structure → StructureUnits → StructureUnitParts
- Structure → Containers → StructureUnit (visual)

#### Buscar Structure por ID
**GET** `/structures/{id}`

Retorna structure específica se pertencer ao usuário.

#### Atualizar Structure
**PUT** `/structures/{id}`
```json
{
  "nome": "Nome Atualizado",
  "tipo": "CENARIO"
}
```

#### Deletar Structure
**DELETE** `/structures/{id}`

Deleta apenas se pertencer ao usuário.

### Sistema de Favoritas

Usuários podem marcar Structures e StructureUnits como favoritas.

#### Adicionar/Remover Structure Favorita
**PUT** `/structures/{id}/favorita?acao=1`
- `acao=1`: Adicionar às favoritas
- `acao=0`: Remover das favoritas

**Validações:**
- Não permite duplicados (usa Set internamente)
- Retorna erro se tentar adicionar existente
- Retorna erro se tentar remover inexistente

#### Adicionar/Remover StructureUnit Favorita
**PUT** `/structures/units/{id}/favorita?acao=1`
- `acao=1`: Adicionar às favoritas
- `acao=0`: Remover das favoritas

#### Listar Favoritas
**GET** `/usuarios/favoritas?nome=casa&tipo=HOME`

Retorna ambas as listas com filtros opcionais:
```json
{
  "structures": [
    {
      "id": 1,
      "nome": "Casa Principal",
      "tipo": "HOME_PLAYER",
      "nomeTipo": "HOME_PLAYER"
    }
  ],
  "structureUnits": [
    {
      "id": 10,
      "nome": "Porta de Madeira",
      "tipo": "PADRAO",
      "nomeTipo": "PADRAO",
      "layer": "FOREGROUND"
    }
  ]
}
```

**Filtros:**
- `nome`: Busca por nome da entidade (case-insensitive, contains)
- `tipo`: Busca por nome do enum tipo (case-insensitive, contains)

### Import/Export JSON

Sistema para clonar structures visualmente (sem IDs).

#### Exportar Structure
**GET** `/structures/{id}/export`

Retorna JSON sem IDs, apenas estrutura visual:
```json
{
  "nome": "Casa Modelo",
  "tipo": "HOME_PLAYER",
  "structureUnits": [
    {
      "nome": "Parede Frontal",
      "layer": "FOREGROUND",
      "tipo": "PADRAO",
      "parts": [
        {
          "nome": "Tijolo 1",
          "inicioX": 0, "inicioY": 0, "inicioZ": 0,
          "fimX": 10, "fimY": 10, "fimZ": 10,
          "materialId": 1,
          "colorMaterialId": 2,
          "areaContato": false
        }
      ]
    }
  ],
  "containers": [
    {
      "structureUnit": {
        "nome": "Baú Visual",
        "layer": "FOREGROUND",
        "tipo": "PADRAO",
        "parts": [...]
      }
    }
  ]
}
```

#### Importar Structure
**POST** `/structures/import`

Cria nova structure idêntica visualmente:
- Vincula ao usuário logado
- Gera novos IDs
- Persiste toda a hierarquia

#### Exportar StructureUnit
**GET** `/structures/units/{id}/export`

Retorna JSON da StructureUnit (sem IDs).

#### Importar StructureUnit
**POST** `/structures/units/import`

Cria nova StructureUnit:
- Vincula ao usuário logado
- Pode ser usada independentemente ou em Structure

### Modelo de Dados: Usuario

```java
@Entity
public class Usuario {
    @ManyToMany
    @JoinTable(name = "usuario_structure_favorita")
    private Set<Structure> structureFavoritas = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "usuario_structure_unit_favorita")
    private Set<StructureUnit> structureUnitFavoritas = new HashSet<>();
}
```

**Usa Set (não List):**
- Impede duplicados automaticamente
- Mais eficiente para verificação de existência
- Melhor performance em operações de add/remove

## StructureService

### Métodos Principais

#### CRUD Manual

##### `listarPorUsuarioComHierarquia(Long usuarioId)`

Lista structures do usuário com hierarquia completa.

**Hierarquia retornada:**
- Structure
  - StructureUnits (com Parts)
  - Containers (com StructureUnit visual)

##### `buscarPorIdComHierarquia(Long id, Long usuarioId)`

Busca structure por ID com hierarquia completa, apenas se pertencer ao usuário.

##### `criar(Usuario usuario, CriarStructureRequest request)`

Cria structure vinculada ao usuário.

**Características:**
- Campo `tipo` pode ser null
- Campos `ilha`, `arenaPvp` não são definidos (criação manual)
- `usuario` é obrigatório (extraído do JWT)

##### `atualizar(Long id, Long usuarioId, AtualizarStructureRequest request)`

Atualiza structure apenas se pertencer ao usuário.

##### `deletar(Long id, Long usuarioId)`

Deleta structure apenas se pertencer ao usuário.

#### Favoritas

##### `adicionarOuRemoverStructureFavorita(Usuario usuario, Long structureId, Integer acao)`

Gerencia favoritas de Structure.

**Parâmetros:**
- `acao=1`: Adicionar
- `acao=0`: Remover

**Validações:**
- Ao adicionar: lança exceção se já existe
- Ao remover: lança exceção se não existe
- Set impede duplicados automaticamente

##### `adicionarOuRemoverStructureUnitFavorita(Usuario usuario, Long structureUnitId, Integer acao)`

Gerencia favoritas de StructureUnit (mesma lógica).

#### Import/Export

##### `exportStructure(Long id, Long usuarioId)`

Exporta Structure para formato JSON (sem IDs).

**Retorna:** JsonStructure com:
- nome, tipo
- structureUnits[] (hierarquia completa)
- containers[] (apenas structureUnit)

##### `importStructure(Usuario usuario, JsonStructure json)`

Importa Structure de JSON.

**Processo:**
1. Cria Structure vinculada ao usuário
2. Converte e persiste StructureUnits com Parts
3. Converte e persiste Containers com StructureUnit visual
4. Retorna StructureResponse

##### `exportStructureUnit(Long id, Long usuarioId)`

Exporta StructureUnit para JSON (sem IDs).

##### `importStructureUnit(Usuario usuario, JsonStructureUnit json)`

Importa StructureUnit de JSON.

**Processo:**
1. Cria StructureUnit vinculada ao usuário
2. Converte e persiste Parts
3. Retorna StructureUnitResponse

#### Métodos Auxiliares

##### `convertStructureUnitToJson(StructureUnit unit)`

Converte entidade para formato JSON (sem IDs).

##### `convertJsonToStructureUnit(JsonStructureUnit json, Usuario usuario)`

Converte JSON para entidade e persiste.

#### Criação Automática (Player)

##### `criarHomePlayer(Player player)`

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

## BaseStructureRepository

### Métodos

#### `findByRacaAndTipo(PlayerRaca raca, StructureTipo tipo)`

Busca configurações base ativas para uma raça e tipo específicos.

**Exemplo:**
```java
List<BaseStructure> templates = repository.findByRacaAndTipo(
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
- StructureUnits e Containers NÃO são copiados

**Futuro (Implementar):**
- Cópia profunda de StructureUnits
- Cópia profunda de Containers
- Cópia de StructureUnitParts
- Ajuste de posições relativas

**Alternativa Atual:**
- Use Import/Export JSON para clonar structures visualmente

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

### Novos Arquivos (Sistema Automático)

1. `BaseStructure.java` - Entidade de configuração base (antes BaseStructureTipo)
2. `BaseStructureRepository.java` - Repository com query customizada
3. `StructureService.java` - Service com lógica de negócio (criação automática + CRUD + import/export)
4. `StructureTipoConverter.java` - Converter para HTTP

### Novos Arquivos (CRUD Manual)

5. `StructureResource.java` - Endpoints REST para CRUD, favoritas e import/export
6. `CriarStructureRequest.java` - DTO para criação
7. `AtualizarStructureRequest.java` - DTO para atualização
8. `StructureResponse.java` - DTO de resposta com hierarquia completa
9. `StructureUnitResponse.java` - DTO de StructureUnit
10. `StructureUnitPartResponse.java` - DTO de StructureUnitPart
11. `ContainerResponse.java` - DTO de Container (com structureUnit)

### Novos Arquivos (Import/Export JSON)

12. `JsonStructure.java` - DTO JSON para import/export
13. `JsonStructureUnit.java` - DTO JSON de StructureUnit
14. `JsonStructureUnitPart.java` - DTO JSON de Part
15. `JsonContainer.java` - DTO JSON de Container

### Novos Arquivos (Favoritas)

16. `StructureFavoritaResponse.java` - DTO para Structure favorita
17. `StructureUnitFavoritaResponse.java` - DTO para StructureUnit favorita
18. `FavoritasResponse.java` - DTO que agrupa ambas listas

### Arquivos Modificados

1. `Structure.java` - Adicionado campo `nome` (nullable)
2. `StructureUnit.java` - Adicionado `nome` e `usuario` (ManyToOne)
3. `StructureUnitPart.java` - Adicionado campo `nome` (nullable)
4. `Usuario.java` - Adicionado favoritas (Set<Structure>, Set<StructureUnit>)
5. `UsuarioService.java` - Adicionado buscarFavoritas() com filtros
6. `UsuarioResource.java` - Adicionado endpoint GET /usuarios/favoritas
7. `StructureTipo.java` - Adicionado `fromId()` e `getId()`
8. `PlayerService.java` - Injeção de StructureService e criação automática de HOME_PLAYER
9. `EnumConverterProvider.java` - Registrado StructureTipoConverter

### Renamings Realizados

- `BaseStructureTipo` → `BaseStructure`
- `ItemStructure` → `StructureUnit`
- `ItemStructurePart` → `StructureUnitPart`
- `BaseContainerItemStructure` → `BaseContainerStructureUnit`

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

O sistema de estruturas é completo e versátil:

1. **Criação Automática**: Cada player recebe HOME_PLAYER baseada na raça com seleção aleatória ponderada
2. **CRUD Manual**: Usuários podem criar structures customizadas independentemente
3. **Import/Export JSON**: Permite clonar structures visualmente sem IDs
4. **Sistema de Favoritas**: Gerenciamento de favorites com Set (sem duplicados)
5. **Hierarquia Completa**: Responses incluem toda a hierarquia (Structure → StructureUnits → Parts, Containers)
6. **Controle de Propriedade**: Usuário vinculado a Structure e StructureUnit
7. **Filtros de Busca**: Busca favoritas por nome e tipo

A arquitetura está preparada para expansão com novos tipos de estruturas (lojas, empresas, guildas) seguindo o mesmo padrão.
