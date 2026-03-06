# Dockerfile para Cupula Game API
# Executa em modo dev para garantir que import.sql e MyInitializer rodem
# Usa arquivo .env copiado para dentro da imagem

FROM maven:3.9.9-amazoncorretto-21

WORKDIR /app

# Instalar curl para healthcheck
RUN yum install -y curl && yum clean all

# Copiar arquivos do projeto
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

# Copiar arquivo .env para dentro da imagem
COPY .env /app/.env

# Dar permissão de execução ao mvnw
RUN chmod +x ./mvnw

# Criar usuário não-root para segurança
RUN groupadd -r appgroup && useradd -r -g appgroup appuser && \
    chown -R appuser:appgroup /app

USER appuser

# Expor porta (padrão 8080, pode ser sobrescrita pelo .env)
EXPOSE 8080

# Healthcheck
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:${PORT:-8080}/q/health || exit 1

# Script de inicialização que carrega as variáveis do .env
CMD ["sh", "-c", "\
    set -a && \
    . /app/.env && \
    set +a && \
    ./mvnw compile quarkus:dev \
        -Dquarkus.datasource.jdbc.url=jdbc:mariadb://${DB_HOST}:${DB_PORT}/${DB_NAME}?allowMultiQueries=true\\&sessionVariables=foreign_key_checks=0 \
        -Dquarkus.datasource.username=${DB_USER} \
        -Dquarkus.datasource.password=${DB_PASSWORD} \
        -Dquarkus.http.host=0.0.0.0 \
        -Dquarkus.http.port=${PORT:-8080}"]
