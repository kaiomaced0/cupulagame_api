# Estrutura de Autenticação - Usuário e Player

## 📋 Arquitetura Implementada

### Conceitos
- **Usuário**: Conta principal na plataforma (pode ter múltiplos players)
- **Player**: Personagem do jogo (pertence a um usuário)
- **Token**: JWT contendo informações de autenticação

### Tipos de Token

#### 1. Token de Usuário
```json
{
  "sub": "0Glacks",           // nickname do usuário
  "groups": ["User"],         // roles/perfis
  "exp": 1746123456           // expiração
}
```

#### 2. Token de Player (Usuário + Player)
```json
{
  "sub": "0Glacks",           // nickname do usuário
  "groups": ["User"],         // roles/perfis
  "playerId": 42,             // ID do player logado
  "exp": 1746123456           // expiração
}
```

## 🔐 Fluxo de Autenticação

### Cenário 1: Login apenas como Usuário (Celular)

```
1. Cliente → POST /auth/login
   Body: { "login": "0Glacks", "senha": "senha123" }

2. Servidor valida credenciais

3. Servidor ← Retorna token de USUÁRIO
   {
     "id": 1,
     "nickName": "0Glacks",
     "token": "eyJhbGc..." // SEM playerId
   }

4. Cliente pode:
   - Ver lista de players (GET /players/meus)
   - Criar novo player (POST /players)
   - Acessar dados da conta
```

### Cenário 2: Login como Player (PC/Console/Mobile com player)

```
1. Cliente → POST /auth/login
   Body: { "login": "0Glacks", "senha": "senha123" }
   
   Recebe token de USUÁRIO

2. Cliente → GET /players/meus
   Header: Authorization: Bearer {token_usuario}
   
   Recebe lista: [
     { id: 10, nickName: "ElfoDaNoite", raca: "ELFO" },
     { id: 11, nickName: "GuerreiroOrc", raca: "ORC" },
     { id: 12, nickName: "AnaoFerreiro", raca: "ANAO" }
   ]

3. Usuário escolhe player 10

4. Cliente → POST /auth/player
   Header: Authorization: Bearer {token_usuario}
   Body: { "playerId": 10 }

5. Servidor valida que player 10 pertence ao usuário

6. Servidor ← Retorna token de PLAYER
   {
     "playerId": 10,
     "token": "eyJhbGc..." // COM playerId no claim
   }

7. Cliente pode:
   - Mover player (PUT /players/10/posicao)
   - Alterar nickname (PUT /players/10/nickname)
   - Interagir no jogo
```

## 🌐 Múltiplos Dispositivos Simultâneos

### ✅ Suportado:
- **Celular A**: Logado como usuário (sem player) - gerencia conta
- **PC**: Logado como usuário + Player #10 - jogando
- **Celular B**: Logado como usuário + Player #11 - jogando
- **Console**: Logado como usuário + Player #12 - jogando

### 🔑 Como funciona:
1. Cada dispositivo tem seu próprio token
2. Tokens são independentes (não há limite)
3. Não há sessão no servidor (JWT stateless)
4. Cada dispositivo pode ter um player diferente OU nenhum player

## 📡 Endpoints de Autenticação

### POST `/auth/login`
```json
Request:
{
  "login": "0Glacks",
  "senha": "senha123"
}

Response:
{
  "id": 1,
  "nickName": "0Glacks",
  "email": "0glacks@cupula.com",
  "loginLocalHabilitado": true,
  "perfis": ["USER"],
  "providers": [],
  "token": "eyJhbGc..."
}
```

### POST `/auth/provider`
```json
Request:
{
  "provider": 1,  // 0=LOCAL, 1=GOOGLE, 2=FACEBOOK, 3=DISCORD
  "externalId": "google-user-123",
  "email": "user@gmail.com",
  "displayName": "User Name"
}

Response: (mesmo formato de /auth/login)
```

### POST `/auth/player`
**Requer:** Token de usuário válido

```json
Request:
{
  "playerId": 10
}

Response:
{
  "playerId": 10,
  "token": "eyJhbGc..."  // novo token COM playerId
}
```

### GET `/auth/me`
**Requer:** Token válido

```json
Response:
{
  "id": 1,
  "nickName": "0Glacks",
  "email": "0glacks@cupula.com",
  "loginLocalHabilitado": true,
  "perfis": ["USER"],
  "providers": [],
  "players": [
    { "id": 10, "nickName": "ElfoDaNoite", "raca": "ELFO" },
    { "id": 11, "nickName": "GuerreiroOrc", "raca": "ORC" }
  ]
}
```

## 🎮 Endpoints de Player

### POST `/players`
**Requer:** Token de usuário (User/Admin)

Cria novo player para o usuário logado.

```json
Request:
{}  // CriarPlayerRequest vazio - atributos gerados automaticamente

Response:
{
  "id": 15,
  "usuarioId": 1,
  "visibilidade": "AMIGOS",
  "raca": "HUMANO",
  "tamanhoX": 180,
  "tamanhoY": 180,
  "tamanhoZ": 180,
  "tipoCabeloId": 5,
  "nivelAtual": 1,
  "xpAtual": 0
}
```

### GET `/players/meus`
**Requer:** Token de usuário (User/Admin)

Lista todos os players do usuário logado.

```json
Response:
[
  { "id": 10, "raca": "ELFO", ... },
  { "id": 11, "raca": "ORC", ... }
]
```

### GET `/players`
**Público** - Lista todos os players do sistema.

### GET `/players/{id}`
**Público** - Busca player específico.

### PUT `/players/{id}/nickname`
**Requer:** Token de usuário (User/Admin) + ownership do player

```json
Request:
{
  "nickName": "NovoNome",
  "tag": "1234"
}
```

### PUT `/players/{id}/posicao`
**Requer:** Token de player OU token de usuário que possui o player

```json
Request:
{
  "x": 100,
  "y": 50,
  "z": 200
}
```

**Validação:**
- Se tem `playerId` no token E `playerId == id` → ✅ autorizado (está jogando)
- OU se o player pertence ao usuário logado → ✅ autorizado (pode mover qualquer player seu)

## 🔒 Segurança Implementada

### Correções Realizadas:

1. ✅ **Removido endpoint duplicado** `/players/{id}/login` - mantido apenas `/auth/player`

2. ✅ **POST /players agora requer autenticação**
   - Antes: `@PermitAll` (qualquer um criava player)
   - Agora: `@RolesAllowed({"User", "Admin"})` + player associado ao usuário logado

3. ✅ **PUT /players/{id}/nickname valida ownership**
   - Antes: `@PermitAll` (qualquer um alterava qualquer player)
   - Agora: Exige autenticação + verifica se player pertence ao usuário

4. ✅ **PUT /players/{id}/posicao valida autorização**
   - Antes: `@PermitAll` (qualquer um movia qualquer player)
   - Agora: Exige que ESTEJA JOGANDO com o player OU que o player pertença ao usuário

## 💡 Exemplos de Uso

### Exemplo 1: Usuário gerenciando conta no celular

```bash
# Login como usuário
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"login":"0Glacks","senha":"senha123"}'

# Resposta: { "token": "TOKEN_USUARIO" }

# Listar meus players
curl -X GET http://localhost:8080/players/meus \
  -H "Authorization: Bearer TOKEN_USUARIO"

# Criar novo player
curl -X POST http://localhost:8080/players \
  -H "Authorization: Bearer TOKEN_USUARIO" \
  -H "Content-Type: application/json" \
  -d '{}'
```

### Exemplo 2: Usuário jogando no PC

```bash
# 1. Login como usuário
curl -X POST http://localhost:8080/auth/login \
  -d '{"login":"0Glacks","senha":"senha123"}'

# 2. Login como player #10
curl -X POST http://localhost:8080/auth/player \
  -H "Authorization: Bearer TOKEN_USUARIO" \
  -d '{"playerId": 10}'

# Resposta: { "playerId": 10, "token": "TOKEN_PLAYER" }

# 3. Mover player (usando token de player)
curl -X PUT http://localhost:8080/players/10/posicao \
  -H "Authorization: Bearer TOKEN_PLAYER" \
  -d '{"x":100,"y":50,"z":200}'
```

## 🎯 Casos de Uso Suportados

| Dispositivo | Cenário | Token | Capacidades |
|-------------|---------|-------|-------------|
| Celular | Gerenciar conta | Usuário | Criar players, ver lista, alterar configurações |
| PC | Jogando com Player #1 | Usuário + Player 1 | Todas as ações do player 1 |
| Tablet | Jogando com Player #2 | Usuário + Player 2 | Todas as ações do player 2 |
| Console | Jogando com Player #3 | Usuário + Player 3 | Todas as ações do player 3 |

**✅ Todos simultâneos!** Não há conflito de sessão.

## 🔍 Validações de Segurança

### Endpoint: POST /players
- ✅ Requer autenticação
- ✅ Player criado é automaticamente associado ao usuário logado
- ❌ Impossível criar player órfão

### Endpoint: PUT /players/{id}/nickname
- ✅ Requer autenticação
- ✅ Valida que player pertence ao usuário
- ❌ Impossível alterar nickname de player de outro usuário

### Endpoint: PUT /players/{id}/posicao  
- ✅ Requer autenticação
- ✅ Valida ownership OU que está jogando como o player
- ❌ Impossível mover player de outro usuário (a não ser que seja admin)

### Endpoint: POST /auth/player
- ✅ Requer autenticação
- ✅ Valida que player existe e pertence ao usuário
- ❌ Impossível logar como player de outro usuário

## 🚀 Próximos Passos Recomendados

1. **Rate Limiting**: Limitar tentativas de login
2. **Refresh Tokens**: Para renovar JWT sem re-login
3. **Logout**: Blacklist de tokens (requer Redis/cache)
4. **Auditoria**: Log de logins e ações críticas
5. **2FA**: Autenticação de dois fatores (opcional)
6. **Session Management**: Listar dispositivos/sessões ativas (requer persistência)
