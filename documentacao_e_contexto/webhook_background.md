# Webhook BackgroundAsset - Configuração Frontend

## ✅ Status do Webhook (Backend)

O webhook está implementado e funcional no backend:

**Como funciona:**
- Quando um admin atribui um background a um usuário (`PUT /background-assets/assign`), o backend envia automaticamente um **HTTP POST** para a URL configurada
- Payload enviado:
```json
{
  "usuarioId": 123,
  "backgroundAssetId": 5,
  "backgroundAssetNome": "Background Noturno",
  "documentoId": 456
}
```

**Configuração no Backend** (`application.properties`):
```properties
# URL do frontend que receberá as notificações
cupula.background.webhook.url=http://localhost:3000/api/webhooks/background-changed
```

---

## 📋 O que o Frontend precisa implementar:

### 1. **Criar endpoint para receber webhook:**

```javascript
// Exemplo: /api/webhooks/background-changed
// POST endpoint que recebe:
{
  "usuarioId": number,
  "backgroundAssetId": number,
  "backgroundAssetNome": string,
  "documentoId": number
}
```

### 2. **Processar a notificação:**
- Identificar se o usuário está conectado (WebSocket/Socket.io)
- Atualizar o background em tempo real na tela dele
- Buscar o documento completo se necessário: `GET /documentos/{documentoId}`

### 3. **Fluxo sugerido:**
```
Backend envia webhook → Frontend recebe POST → Identifica usuário conectado → 
Envia mensagem via WebSocket/Socket.io → Cliente atualiza UI
```

### 4. **Exemplo de implementação (Node.js/Express):**
```javascript
app.post('/api/webhooks/background-changed', (req, res) => {
  const { usuarioId, backgroundAssetId, documentoId } = req.body;
  
  // Notificar usuário via WebSocket
  io.to(`user-${usuarioId}`).emit('background-updated', {
    backgroundAssetId,
    documentoId
  });
  
  res.status(200).json({ received: true });
});
```

---

## 🔧 Configuração Necessária:

### 1. **No Backend** - Adicione no `application.properties`:
```properties
cupula.background.webhook.url=http://localhost:3000/api/webhooks/background-changed
```

### 2. **No Frontend** - Crie o endpoint webhook e integre com seu sistema de notificação em tempo real (WebSocket/Socket.io)

---

## 📝 Observações Importantes:

- O webhook é disparado **apenas** quando um **admin** faz a atribuição manual via `PUT /background-assets/assign`
- Quando o próprio usuário faz login e busca seu background via `GET /background-assets/me`, não há webhook (não é necessário)
- O endpoint webhook no frontend deve responder com status 200 para confirmar recebimento
- Em caso de erro no envio, o backend loga o erro mas não interrompe a operação de atribuição

---

## 🚀 Endpoints Backend Relacionados:

### Admin - Atribuir Background (dispara webhook)
```http
PUT /background-assets/assign
Authorization: Bearer {token}
Content-Type: application/json

{
  "usuarioId": 123,
  "backgroundAssetId": 5
}
```

### Usuário - Buscar Próprio Background (sem webhook)
```http
GET /background-assets/me
Authorization: Bearer {token}
```

### Buscar Documento Completo
```http
GET /documentos/{documentoId}
Authorization: Bearer {token}
```
