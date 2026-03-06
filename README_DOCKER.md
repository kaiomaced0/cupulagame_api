# 🐳 Deploy com Docker

Este guia explica como fazer deploy da API Cupula Game usando Docker.

## 📋 Pré-requisitos

- Docker instalado
- MariaDB rodando (pode ser externo ao container)
- Arquivo `.env` configurado

## 🚀 Quick Start

### 1. Configurar variáveis de ambiente

```bash
# Copiar template
cp .env.example .env

# Editar com suas configurações
nano .env
```

Exemplo de `.env`:
```env
DB_HOST=192.168.1.100
DB_PORT=3306
DB_NAME=cupulagame_db
DB_USER=root
DB_PASSWORD=SuaSenhaSegura123
PORT=8080
```

### 2. Deploy usando script

```bash
chmod +x deploy.sh
./deploy.sh
```

### 3. Deploy manual

```bash
# Build
docker build -t cupulagame-api:latest .

# Run
docker run -d \
  --name cupulagame-api \
  --restart unless-stopped \
  -p 8080:8080 \
  cupulagame-api:latest
```

## 📊 Monitoramento

```bash
# Ver logs
docker logs -f cupulagame-api

# Ver logs dos seeds
docker logs cupulagame-api | grep "Seeds"

# Verificar saúde
curl http://localhost:8080/q/health

# Acessar Swagger UI
open http://localhost:8080/q/swagger-ui

# Ver recursos
docker stats cupulagame-api
```

## 🛠️ Comandos úteis

```bash
# Parar
docker stop cupulagame-api

# Reiniciar
docker restart cupulagame-api

# Remover
docker rm -f cupulagame-api

# Ver logs em tempo real
docker logs -f --tail 100 cupulagame-api

# Executar comando dentro do container
docker exec -it cupulagame-api sh
```

## 🔐 Segurança

⚠️ **IMPORTANTE:** O arquivo `.env` é copiado para dentro da imagem Docker.

- NÃO faça push dessa imagem para registries públicos
- Use registry privado se precisar compartilhar a imagem
- Para produção, considere usar Docker Secrets ou Kubernetes Secrets

## 🐛 Troubleshooting

### Container não inicia

```bash
# Ver logs de erro
docker logs cupulagame-api

# Verificar se .env está correto
docker exec cupulagame-api cat /app/.env
```

### Não conecta ao banco

```bash
# Testar conexão do host
mysql -h DB_HOST -u DB_USER -p

# Verificar variáveis dentro do container
docker exec cupulagame-api sh -c 'echo $DB_HOST'
```

### Seeds não rodaram

```bash
# Verificar logs
docker logs cupulagame-api | grep -E "(Seeds|ColorMaterial|ItemTipo)"
```

Deve aparecer:
```
=== Iniciando Seeds ===
✓ ColorMaterials criados (23 registros)
✓ ItemTipos criados (80 registros)
...
=== Seeds concluídos ===
```

## 🎯 Características importantes

1. **Modo dev do Quarkus**: Garante que `import.sql` e `MyInitializer` sejam executados
2. **Variáveis de ambiente**: Usa arquivo `.env` copiado para a imagem
3. **Healthcheck**: Verifica automaticamente se a API está saudável
4. **Script de deploy**: Automatiza todo o processo
5. **Usuário não-root**: Roda com usuário dedicado para segurança
6. **Restart policy**: Container reinicia automaticamente em caso de falha

## 📦 Estrutura de arquivos criados

```
cupulagame_api/
├── Dockerfile              # Configuração Docker
├── .env.example           # Template de configuração
├── .env                   # Configuração real (não commitado)
├── deploy.sh              # Script de deploy automatizado
├── README_DOCKER.md       # Documentação Docker
├── .dockerignore          # Arquivos ignorados no build
└── .gitignore             # Atualizado para ignorar .env
```

## ⚠️ Notas

- O arquivo `.env` real NUNCA deve ser commitado (já está no .gitignore)
- O `.env.example` serve como template e deve ser commitado
- O container roda em modo dev para garantir execução dos seeds
- A porta padrão é 8080 mas pode ser alterada no .env
