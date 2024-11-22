FROM gradle:jdk17-alpine AS build

WORKDIR /app

ADD . .
RUN chmod +x ./gradlew

RUN ./gradlew clean bootJar

FROM eclipse-temurin:17-jdk-alpine

COPY --from=build /app/build/libs/*.jar /app.jar

ENV TZ=America/Sao_Paulo

ENTRYPOINT ["java", "-jar", "/app.jar"]