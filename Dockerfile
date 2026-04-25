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
