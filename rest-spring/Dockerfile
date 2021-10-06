FROM adoptopenjdk/openjdk11
CMD ./gradlew build
COPY build/libs/rest-spring-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
