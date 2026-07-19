# ---- build stage ----
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon -q || true

COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# ---- runtime stage ----
FROM eclipse-temurin:21-jre-jammy AS runtime
WORKDIR /app

RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser

COPY --from=builder /app/build/libs/mongo-callector-service-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
