FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY ./build/libs/*.jar .
ENTRYPOINT ["java", "-jar", "todo-app.jar"]