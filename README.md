# Projeto - Cidades ESG Inteligentes

API REST desenvolvida em **Java Spring Boot** para cadastro de cidades e indicadores ESG.

O projeto foi preparado para a atividade **Aplicando Técnicas de DevOps**, contendo:

- Pipeline CI/CD com GitHub Actions.
- Dockerfile funcional.
- Orquestração com Docker Compose.
- Banco de dados PostgreSQL.
- Ambientes simulados de staging e produção.
- Testes automatizados com JUnit.

---

## Como executar localmente com Docker

### 1. Pré-requisitos

Instale:

- Docker
- Docker Compose
- Git

Não é obrigatório instalar Java ou Maven para rodar com Docker, pois o build é feito dentro do container.

### 2. Subir staging e produção

Na raiz do projeto, execute:

```bash
docker compose up --build
```

Esse comando sobe quatro serviços:

- `postgres-staging`
- `app-staging`
- `postgres-production`
- `app-production`

### 3. Acessar os ambientes

Ambiente de staging:

```text
http://localhost:8081/api/cidades/status
```

Ambiente de produção:

```text
http://localhost:8082/api/cidades/status
```

Swagger / documentação da API:

```text
http://localhost:8081/swagger-ui.html
http://localhost:8082/swagger-ui.html
```

### 4. Exemplo de cadastro de cidade

```bash
curl -X POST http://localhost:8081/api/cidades \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Guarulhos",
    "estado": "SP",
    "notaAmbiental": 80,
    "notaSocial": 75,
    "notaGovernanca": 90
  }'
```

Listar cidades:

```bash
curl http://localhost:8081/api/cidades
```

---

## Pipeline CI/CD

A ferramenta utilizada foi o **GitHub Actions**.

Arquivo do pipeline:

```text
.github/workflows/ci-cd.yml
```

### Etapas do pipeline

1. **Checkout do código**
   - Baixa o repositório para o runner do GitHub Actions.

2. **Configuração do Java 17**
   - Prepara o ambiente para build da aplicação Spring Boot.

3. **Build automático**
   - Executa:
   ```bash
   mvn clean package -DskipTests
   ```

4. **Testes automatizados**
   - Executa:
   ```bash
   mvn test
   ```

5. **Build da imagem Docker**
   - Executa:
   ```bash
   docker build -t cidades-esg-inteligentes:latest .
   ```

6. **Deploy Staging**
   - Sobe o ambiente de staging usando Docker Compose:
   ```bash
   docker compose up -d postgres-staging app-staging
   ```

7. **Deploy Production**
   - Sobe o ambiente de produção usando Docker Compose:
   ```bash
   docker compose up -d postgres-production app-production
   ```

8. **Validação dos ambientes**
   - O pipeline valida os endpoints:
   ```text
   http://localhost:8081/api/cidades/status
   http://localhost:8082/api/cidades/status
   ```

---

## Containerização

O projeto utiliza um Dockerfile multi-stage.

### Dockerfile

```dockerfile
# Etapa 1: build da aplicação usando Maven e Java 17
FROM maven:3.9.8-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: imagem final mais leve para execução
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/target/cidades-esg-inteligentes.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Estratégia adotada

Foi usada a estratégia **multi-stage build**, separando:

- etapa de build com Maven;
- etapa de execução com JRE leve.

Isso reduz o tamanho da imagem final e aproxima o ambiente de produção de uma execução real.

---

## Orquestração com Docker Compose

O arquivo `docker-compose.yml` cria dois ambientes:

### Staging

- API na porta `8081`
- PostgreSQL exposto na porta `5433`

### Produção

- API na porta `8082`
- PostgreSQL exposto na porta `5434`

Foram utilizados:

- volumes para persistência dos dados;
- variáveis de ambiente para configurar conexão com banco;
- rede Docker dedicada;
- healthcheck para garantir que o banco esteja pronto antes da API subir.

---

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/cidades/status` | Verifica status da API |
| GET | `/api/cidades` | Lista cidades |
| GET | `/api/cidades/{id}` | Busca cidade por ID |
| POST | `/api/cidades` | Cadastra cidade |
| PUT | `/api/cidades/{id}` | Atualiza cidade |
| DELETE | `/api/cidades/{id}` | Remove cidade |

---

## Prints do funcionamento

> Substitua os itens abaixo pelos prints reais após executar o projeto.

### Print 1 - GitHub Actions rodando

Inserir print do workflow em execução.

### Print 2 - Build concluído

Inserir print da etapa de build com sucesso.

### Print 3 - Testes automatizados

Inserir print da etapa `mvn test` concluída.

### Print 4 - Docker Compose

Inserir print do comando:

```bash
docker compose up --build
```

### Print 5 - Containers em execução

Inserir print do comando:

```bash
docker ps
```

### Print 6 - Staging funcionando

Inserir print do navegador acessando:

```text
http://localhost:8081/api/cidades/status
```

### Print 7 - Produção funcionando

Inserir print do navegador acessando:

```text
http://localhost:8082/api/cidades/status
```

---

## Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL
- H2 Database para testes
- Docker
- Docker Compose
- GitHub Actions
- Maven
- Swagger / OpenAPI

---

## Checklist de Entrega

| Item | OK |
|---|---|
| Projeto compactado em .ZIP com estrutura organizada | ☑ |
| Dockerfile funcional | ☑ |
| docker-compose.yml ou arquivos Kubernetes | ☑ |
| Pipeline com etapas de build, teste e deploy | ☑ |
| README.md com instruções e prints | ☐ |
| Documentação técnica com evidências (PDF ou PPT) | ☐ |
| Deploy realizado nos ambientes staging e produção | ☐ |
