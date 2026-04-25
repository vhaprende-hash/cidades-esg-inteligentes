# Documentação Técnica - Cidades ESG Inteligentes

## Integrantes

- Nome:
- RM:

---

## 1. Descrição do projeto

O projeto Cidades ESG Inteligentes é uma API REST para cadastro de cidades e cálculo de índice ESG com base em três dimensões:

- Ambiental
- Social
- Governança

A aplicação foi desenvolvida em Java Spring Boot e utiliza PostgreSQL como banco de dados.

---

## 2. Pipeline CI/CD

Ferramenta utilizada: GitHub Actions.

Arquivo:

```text
.github/workflows/ci-cd.yml
```

Etapas implementadas:

1. Checkout do código.
2. Configuração do Java 17.
3. Build com Maven.
4. Execução de testes automatizados.
5. Build da imagem Docker.
6. Deploy em staging.
7. Deploy em produção.

### Print do pipeline rodando

Inserir print aqui.

### Print da etapa de build

Inserir print aqui.

### Print da etapa de testes

Inserir print aqui.

---

## 3. Docker e arquitetura

A aplicação foi containerizada com Docker utilizando uma estratégia multi-stage build.

Serviços orquestrados no Docker Compose:

- app-staging
- postgres-staging
- app-production
- postgres-production

### Comandos utilizados

```bash
docker compose up --build
docker ps
```

### Print dos containers

Inserir print aqui.

---

## 4. Ambientes

### Staging

URL:

```text
http://localhost:8081/api/cidades/status
```

Print:

Inserir print aqui.

### Produção

URL:

```text
http://localhost:8082/api/cidades/status
```

Print:

Inserir print aqui.

---

## 5. Desafios encontrados e soluções

### Desafio 1

Configurar a aplicação para rodar em containers diferentes para staging e produção.

### Solução

Foram utilizadas variáveis de ambiente no Docker Compose para mudar a conexão com o banco de dados conforme o ambiente.

### Desafio 2

Garantir que a API só subisse após o PostgreSQL estar pronto.

### Solução

Foi configurado healthcheck no PostgreSQL e `depends_on` com `condition: service_healthy`.

---

## 6. Checklist de Entrega

| Item | OK |
|---|---|
| Projeto compactado em .ZIP com estrutura organizada | ☑ |
| Dockerfile funcional | ☑ |
| docker-compose.yml ou arquivos Kubernetes | ☑ |
| Pipeline com etapas de build, teste e deploy | ☑ |
| README.md com instruções e prints | ☑ |
| Documentação técnica com evidências (PDF ou PPT) | ☑ |
| Deploy realizado nos ambientes staging e produção | ☑ |
