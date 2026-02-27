# API Bancária - Documentação

## Visão Geral

Sistema de contas bancárias e transferências para players. Cada player possui uma conta bancária vinculada e pode realizar operações financeiras de forma segura.

## Segurança

- Todas as requisições exigem autenticação JWT
- Player só pode acessar dados da própria conta
- Transferências exigem senha do usuário
- Validação de saldo antes de transferências

---

## Endpoints

### 📊 Conta Bancária

#### `GET /contas-bancarias/me`
Retorna dados básicos da conta do player logado.

**Autenticação:** Requerida (User ou Admin)

**Response:**
```json
{
  "contaId": 123,
  "numeroConta": "12345-6",
  "titular": "João Silva",
  "saldo": 150000,
  "tipo": "CORRENTE",
  "ultimasTransacoes": [
    {
      "id": 456,
      "tipo": "DEBITO",
      "valor": 5000,
      "data": "2026-02-27T10:30:00",
      "numeroOutraConta": "98765-4",
      "nomeOutraConta": "Maria Santos"
    }
  ]
}
```

**Campos:**
- `ultimasTransacoes`: Lista com as 3 transações mais recentes (débitos + créditos)
- `tipo`: Enum com valores possíveis do tipo de conta

---

#### `GET /contas-bancarias/validar/{numeroConta}`
Valida se uma conta existe e retorna o nome do titular.

**Autenticação:** Requerida (User ou Admin)

**Parâmetros:**
- `numeroConta` (path): Número da conta a ser validada

**Response:**
```json
{
  "numeroConta": "98765-4",
  "nomeUsuario": "Maria Santos",
  "contaExiste": true
}
```

**Uso:** Validar o destinatário antes de realizar uma transferência.

---

### 💸 Transações Bancárias

#### `POST /transacoes-bancarias/transferir`
Realiza transferência entre contas.

**Autenticação:** Requerida (User ou Admin)

**Request Body:**
```json
{
  "numeroContaDestino": "98765-4",
  "valor": 5000,
  "senha": "senha123"
}
```

**Validações:**
- Número da conta destino é obrigatório
- Valor deve ser positivo
- Senha é obrigatória e deve estar correta
- Saldo suficiente na conta origem
- Não permite transferir para a mesma conta

**Response:**
```json
{
  "id": 789,
  "valor": 5000,
  "dataTransacao": "2026-02-27T15:45:00",
  "contaOrigemId": 123,
  "numeroContaOrigem": "12345-6",
  "titularContaOrigem": "João Silva",
  "saldoOrigemAntes": 150000,
  "saldoOrigemDepois": 145000,
  "contaDestinoId": 456,
  "numeroContaDestino": "98765-4",
  "titularContaDestino": "Maria Santos",
  "saldoDestinoAntes": 50000,
  "saldoDestinoDepois": 55000
}
```

**Observações:**
- A senha é validada usando hash PBKDF2 (mesmo padrão do login)
- Transação é atômica (sucesso total ou rollback)
- Registra saldos antes e depois da operação

---

#### `GET /transacoes-bancarias/me`
Lista todas as transações do player logado com paginação.

**Autenticação:** Requerida (User ou Admin)

**Query Parameters:**
- `page` (opcional): Número da página (default: 0)
- `size` (opcional): Tamanho da página (default: 10, max: 100)

**Exemplo:** `GET /transacoes-bancarias/me?page=0&size=20`

**Response:**
```json
{
  "content": [
    {
      "id": 789,
      "tipo": "DEBITO",
      "valor": 5000,
      "data": "2026-02-27T15:45:00",
      "numeroOutraConta": "98765-4",
      "nomeOutraConta": "Maria Santos"
    },
    {
      "id": 788,
      "tipo": "CREDITO",
      "valor": 10000,
      "data": "2026-02-26T10:20:00",
      "numeroOutraConta": "11111-1",
      "nomeOutraConta": "Pedro Costa"
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 45,
  "totalPages": 3,
  "first": true,
  "last": false
}
```

**Campos:**
- `content`: Lista de transações da página
- `page`: Número da página atual (zero-indexed)
- `size`: Tamanho da página
- `totalElements`: Total de transações
- `totalPages`: Total de páginas
- `first`: Se é a primeira página
- `last`: Se é a última página

**Observações:**
- `tipo`: "DEBITO" (dinheiro saiu) ou "CREDITO" (dinheiro entrou)
- Lista ordenada por data (mais recentes primeiro)
- Inclui débitos e créditos
- Limite máximo de 100 itens por página

---

#### `GET /transacoes-bancarias/{id}`
Retorna detalhes completos de uma transação específica.

**Autenticação:** Requerida (User ou Admin)

**Parâmetros:**
- `id` (path): ID da transação

**Response:**
```json
{
  "id": 789,
  "valor": 5000,
  "dataTransacao": "2026-02-27T15:45:00",
  "contaOrigemId": 123,
  "numeroContaOrigem": "12345-6",
  "titularContaOrigem": "João Silva",
  "saldoOrigemAntes": 150000,
  "saldoOrigemDepois": 145000,
  "contaDestinoId": 456,
  "numeroContaDestino": "98765-4",
  "titularContaDestino": "Maria Santos",
  "saldoDestinoAntes": 50000,
  "saldoDestinoDepois": 55000
}
```

**Segurança:**
- Player só pode ver transações onde é origem OU destino
- Retorna 403 Forbidden caso contrário

---

## Fluxo de Transferência

### Fluxo Recomendado:

1. **Usuário informa número da conta destino**
   ```
   GET /contas-bancarias/validar/98765-4
   ```
   - Valida se conta existe
   - Exibe nome do titular para confirmação

2. **Usuário confirma e informa valor + senha**
   ```
   POST /transacoes-bancarias/transferir
   {
     "numeroContaDestino": "98765-4",
     "valor": 5000,
     "senha": "senha123"
   }
   ```
   - Sistema valida senha
   - Verifica saldo
   - Realiza transferência
   - Retorna comprovante completo

3. **Atualizar dados da conta**
   ```
   GET /contas-bancarias/me
   ```
   - Mostra novo saldo
   - Lista últimas 3 transações

4. **Ver histórico completo (opcional)**
   ```
   GET /transacoes-bancarias/me?page=0&size=20
   ```
   - Lista todas transações paginadas
   - Ordenadas por data (mais recentes primeiro)

---

## Códigos de Erro

### 400 Bad Request
- Número da conta destino não informado
- Valor não informado ou negativo
- Senha não informada
- Saldo insuficiente
- Tentativa de transferir para mesma conta

### 403 Forbidden
- Senha incorreta
- Usuário sem senha cadastrada
- Tentativa de acessar transação de outro player

### 404 Not Found
- Player não encontrado
- Conta bancária não encontrada
- Transação não encontrada
- Conta destino não existe

---

## Modelos de Dados

### ContaBancaria
- `id`: Long
- `numeroConta`: String (único)
- `titular`: String
- `saldo`: Long (valor em centavos)
- `tipo`: Enum (CORRENTE, POUPANCA, etc.)
- `proprietario`: Player (ManyToOne)

### TransacaoBancaria
- `id`: Long
- `contaOrigem`: ContaBancaria
- `contaDestino`: ContaBancaria
- `valor`: Long
- `contaBancariaOrigemAntes`: Long
- `contaBancariaDestinoAntes`: Long
- `contaBancariaOrigemDepois`: Long
- `contaBancariaDestinoDepois`: Long
- `dataInclusao`: LocalDateTime (herdado de EntityClass)

---

## Observações Técnicas

### JWT Claims
- `playerId`: Identificador do player logado (extraído automaticamente)

### Validação de Senha
- Hash PBKDF2WithHmacSHA512
- Compatível com login existente
- Fallback para senha plain text (desenvolvimento)

### Transações
- Operações de transferência são `@Transactional`
- Rollback automático em caso de erro
- Registra saldos antes/depois para auditoria

### Performance
- Queries otimizadas para listar transações
- Limite de 3 transações em `/me` da conta
- Ordenação por data no banco de dados

### Paginação
- Endpoint `/transacoes-bancarias/me` suporta paginação
- Parâmetros: `page` (default: 0) e `size` (default: 10)
- Limite máximo: 100 itens por página
- Retorna metadados: total de elementos, páginas, primeira/última
- Validação automática de parâmetros inválidos

**Exemplos de uso:**
```
GET /transacoes-bancarias/me              → Primeira página, 10 itens
GET /transacoes-bancarias/me?page=2       → Terceira página, 10 itens
GET /transacoes-bancarias/me?size=50      → Primeira página, 50 itens
GET /transacoes-bancarias/me?page=1&size=25  → Segunda página, 25 itens
```

**Navegação:**
- Use `page + 1` para próxima página
- Verifique `last: true` para identificar última página
- `totalPages` indica quantas páginas existem no total
