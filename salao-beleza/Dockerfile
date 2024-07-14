# Defina a imagem base
FROM openjdk:17-jdk

# Copie o arquivo JAR do seu aplicativo para a imagem
COPY target/salao-beleza-0.0.1-SNAPSHOT.jar /app/app.jar

# Configure o comando para executar o aplicativo
CMD ["java", "-jar", "/app/app.jar"]