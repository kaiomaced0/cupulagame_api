#!/bin/bash

set -e

echo "🚀 Deploy Cupula Game API"

# 1. Verificar se .env existe
if [ ! -f .env ]; then
    echo "❌ Arquivo .env não encontrado!"
    echo "📝 Copie .env.example e configure as variáveis:"
    echo "   cp .env.example .env"
    echo "   nano .env"
    exit 1
fi

# 2. Parar e remover container antigo
echo "🛑 Parando container antigo..."
docker stop cupulagame-api 2>/dev/null || true
docker rm cupulagame-api 2>/dev/null || true

# 3. Build da nova imagem
echo "🔨 Building imagem..."
docker build -t cupulagame-api:latest .

# 4. Executar novo container
echo "▶️ Iniciando container..."
docker run -d \
  --name cupulagame-api \
  --restart unless-stopped \
  -p 8080:8080 \
  cupulagame-api:latest

# 5. Aguardar inicialização
echo "⏳ Aguardando inicialização (60s)..."
sleep 60

# 6. Verificar saúde
echo "🏥 Verificando saúde..."
if curl -f http://localhost:8080/q/health > /dev/null 2>&1; then
    echo "✅ API está rodando!"
    echo "📊 Swagger UI: http://localhost:8080/q/swagger-ui"
else
    echo "❌ API não está respondendo!"
    echo "📋 Logs:"
    docker logs --tail 50 cupulagame-api
    exit 1
fi

echo "🎉 Deploy concluído com sucesso!"
