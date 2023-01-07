FROM openjdk:11

EXPOSE 8080

VOLUME /app
ARG JAR_FILE=build/libs/senha-valida-1.0.0-api.jar
ADD $JAR_FILE /app/api.jar
WORKDIR /app

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./unrandom", "-jar", "api.jar"]