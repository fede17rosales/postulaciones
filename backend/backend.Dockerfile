# Usa una imagen oficial de Maven con JDK 17 para compilar
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el archivo pom.xml y descarga dependencias
COPY ./backend/target/postulacionesApp-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
