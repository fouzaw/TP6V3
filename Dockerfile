# Étape 1 : Build
FROM openjdk:17-slim AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean install -DskipTests

# Étape 2 : Runtime (on réutilise openjdk:17-slim, mais sans les outils de build)
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]