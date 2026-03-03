# Endpoint: Dados do Usuário Logado

## GET /auth/me

Retorna os dados completos do usuário autenticado, incluindo a lista de players associados.

### Autenticação
- **Requerida**: Sim
- **Tipo**: Bearer Token (JWT)
- **Roles permitidas**: `User`, `Admin`

### Headers
```
Authorization: Bearer {token_jwt}
Content-Type: application/json
```

### Request
Não requer body. O usuário é identificado pelo token JWT no header.

### Response Success (200 OK)

```json
{
  "id": 1,
  "nickName": "usuario123",
  "email": "usuario@example.com",
  "loginLocalHabilitado": true,
  "perfis": ["USER"],
  "providers": ["GOOGLE", "LOCAL"],
  "players": [
    {
      "id": 10,
      "nickName": "PlayerNick",
      "raca": "HUMANO"
    },
    {
      "id": 15,
      "nickName": "OutroPlayer",
      "raca": "ELFO"
    }
  ]
}
```

### Response Error

#### 401 Unauthorized
Quando o token não é fornecido, é inválido ou o usuário não existe:
```json
{
  "message": "Unauthorized"
}
```

#### 500 Internal Server Error
Erro ao processar os dados do usuário:
```json
{
  "message": "Internal Server Error"
}
```

### Campos da Resposta

#### UsuarioLogadoDTO
| Campo | Tipo | Nullable | Descrição |
|-------|------|----------|-----------|
| `id` | Long | Não | ID único do usuário |
| `nickName` | String | Não | Nome de usuário/apelido |
| `email` | String | Sim | Email do usuário |
| `loginLocalHabilitado` | Boolean | Sim | Se login local está habilitado |
| `perfis` | Set\<String\> | Sim | Lista de perfis/roles do usuário |
| `providers` | Set\<String\> | Sim | Provedores de autenticação vinculados |
| `players` | List\<PlayerBasicDTO\> | **Sim** | Lista de players do usuário (pode ser null ou vazia) |

#### PlayerBasicDTO
| Campo | Tipo | Nullable | Descrição |
|-------|------|----------|-----------|
| `id` | Long | Não | ID único do player |
| `nickName` | String | Sim | Nome do personagem |
| `raca` | String | **Sim** | Raça do player (pode ser null) |

### Valores de Enum

#### Perfis (perfis)
- `USER` - Usuário comum
- `ADMIN` - Administrador

#### Provedores (providers)
- `LOCAL` - Login local (email/senha)
- `GOOGLE` - Login via Google
- `FACEBOOK` - Login via Facebook
- `APPLE` - Login via Apple
- `MICROSOFT` - Login via Microsoft
- `GITHUB` - Login via GitHub

#### Raças (raca)
Depende das raças disponíveis no sistema. Pode ser `null` se o player não tiver raça definida.

### Exemplo de Uso (JavaScript/Fetch)

```javascript
async function getUsuarioLogado() {
  const token = localStorage.getItem('token'); // ou de onde você armazena o token
  
  try {
    const response = await fetch('http://localhost:8080/auth/me', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });

    if (response.ok) {
      const usuario = await response.json();
      console.log('Usuário:', usuario);
      console.log('Players:', usuario.players);
      return usuario;
    } else if (response.status === 401) {
      console.error('Token inválido ou expirado');
      // Redirecionar para login
    } else {
      console.error('Erro ao buscar usuário');
    }
  } catch (error) {
    console.error('Erro na requisição:', error);
  }
}
```

### Exemplo de Uso (Axios)

```javascript
import axios from 'axios';

async function getUsuarioLogado() {
  try {
    const response = await axios.get('http://localhost:8080/auth/me', {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
    
    console.log('Usuário:', response.data);
    console.log('Players:', response.data.players);
    return response.data;
  } catch (error) {
    if (error.response?.status === 401) {
      console.error('Token inválido ou expirado');
      // Redirecionar para login
    } else {
      console.error('Erro ao buscar usuário:', error);
    }
  }
}
```

### Notas Importantes

1. **Validação de Null**: O campo `players` pode ser `null` ou uma lista vazia. Sempre valide antes de iterar:
   ```javascript
   if (usuario.players && usuario.players.length > 0) {
     usuario.players.forEach(player => {
       console.log(player.nickName);
     });
   }
   ```

2. **Raça Nullable**: O campo `raca` dentro de cada player pode ser `null`:
   ```javascript
   const raca = player.raca || 'Não definida';
   ```

3. **Token Expirado**: Se o token JWT expirar, você receberá 401. Implemente lógica para renovar o token ou redirecionar para login.

4. **Cache**: Considere cachear os dados do usuário no frontend para evitar requisições desnecessárias. Atualize o cache após operações que modifiquem o usuário ou players.

### Casos de Uso

- **Tela de Perfil**: Exibir dados do usuário e lista de personagens
- **Seleção de Player**: Mostrar os players disponíveis para login
- **Dashboard**: Exibir informações do usuário logado
- **Menu**: Mostrar nickname e avatar do usuário
- **Validação de Sessão**: Verificar se o token ainda é válido

### Fluxo Recomendado

1. Usuário faz login → Recebe token JWT
2. Frontend armazena token (localStorage/sessionStorage)
3. Ao carregar aplicação, chama `/auth/me` para validar sessão e obter dados
4. Se sucesso: usuário está autenticado, exibe dados
5. Se 401: redireciona para tela de login
6. Dados do usuário ficam disponíveis globalmente (Context/Store)

### Integração com Player Login

Após obter a lista de players via `/auth/me`, o usuário pode selecionar um player e fazer login com ele usando o endpoint `/auth/player`:

```javascript
// 1. Buscar usuário e players
const usuario = await getUsuarioLogado();

// 2. Usuário seleciona um player
const playerSelecionado = usuario.players[0];

// 3. Fazer login com o player
const response = await fetch('http://localhost:8080/auth/player', {
  method: 'POST',
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    playerId: playerSelecionado.id
  })
});

// 4. Recebe novo token com contexto do player
const { playerId, token: playerToken } = await response.json();

// 5. Atualiza token no storage
localStorage.setItem('token', playerToken);
localStorage.setItem('currentPlayerId', playerId);
```
