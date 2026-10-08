# ---------- Stage 1: build ----------
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copia só o necessário pra resolver dependências primeiro (melhor cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon || true

# Agora copia o resto do código e builda
COPY src src
RUN ./gradlew clean bootJar --no-daemon

# ---------- Stage 2: runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Usuário não-root (boa prática)
RUN addgroup -S app && adduser -S app -G app
USER app

# Copia o jar gerado
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]