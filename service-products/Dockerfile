# ---------- Stage 1: build ----------
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copy only what's necessary to resolve dependencies first (better caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon || true

# Now copy the rest of the build code
COPY src src
RUN ./gradlew clean bootJar --no-daemon

# ---------- Stage 2: runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Non-root user
RUN addgroup -S app && adduser -S app -G app
USER app

# Copy the generated jar file
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]