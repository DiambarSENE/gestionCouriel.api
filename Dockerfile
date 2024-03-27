# Utilisez une image Java 17 comme image de base
FROM openjdk:17

# Définissez le répertoire de travail à l'intérieur du conteneur Docker.
#WORKDIR /app

#Cette ligne copie le fichier JAR généré de votre application Spring Boot
#depuis le répertoire target de votre projet (dans le contexte de construction du Dockerfile)
#vers le répertoire de travail du conteneur, qui a été défini précédemment à l'aide de la commande WORKDIR.
ADD target/yaatout.gestionCouriel.api-0.0.1-SNAPSHOT.jar yaatout.gestionCouriel.api-0.0.1-SNAPSHOT.jar
# Exposer le port sur lequel le serveur sera accessible
EXPOSE 8087

# Commande à exécuter au démarrage du conteneur
ENTRYPOINT ["java", "-jar", "yaatout.gestionCouriel.api-0.0.1-SNAPSHOT.jar"]