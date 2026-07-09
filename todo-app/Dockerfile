FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ENV PORT=8082
COPY ./build/libs/*.jar .
ENTRYPOINT ["java", "-jar", "todo-app.jar"]