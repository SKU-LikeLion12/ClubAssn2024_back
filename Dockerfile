FROM openjdk:17
ARG JAR_FILE=build/libs/puzzle-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]