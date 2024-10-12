# Usa la imagen oficial de OpenJDK como base
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado a la imagen
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
