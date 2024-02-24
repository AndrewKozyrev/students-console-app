FROM openjdk:21-slim

WORKDIR /app

COPY target/students-console-app.jar students-console-app.jar

VOLUME ["/app"]

CMD ["java", "-jar", "students-console-app.jar"]