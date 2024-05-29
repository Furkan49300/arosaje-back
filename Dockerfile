# Utilisez une image de base de JDK
FROM openjdk:17-jdk-alpine

# Configurez un répertoire de travail
WORKDIR /app

# Copiez le fichier JAR de l'application dans l'image Docker
COPY target/arosaje-0.0.1-SNAPSHOT.jar app.jar

# Exposez le port que votre application utilise
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
