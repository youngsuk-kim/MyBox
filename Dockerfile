FROM gradle:jdk17-focal

WORKDIR /app
COPY ./ ./
RUN gradle bootJar

EXPOSE 8080

ENV	USE_PROFILE local

ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar", "core/core-api/build/libs/core-api-0.0.1-SNAPSHOT.jar"]