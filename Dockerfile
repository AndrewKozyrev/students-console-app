FROM openjdk:21-slim

WORKDIR /app

COPY target/students-console-app.jar students-console-app.jar

VOLUME ["/config"]

CMD ["java", "-jar", "students-console-app.jar"]