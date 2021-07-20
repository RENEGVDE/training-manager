FROM azul/zulu-openjdk-alpine:11
ARG JAR_FILE=build/libs//*.jar
COPY ${JAR_FILE} club-rest-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/club-rest-api-0.0.1-SNAPSHOT.jar"]