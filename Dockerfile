FROM adoptopenjdk/openjdk16
VOLUME /tmp
EXPOSE 8080
ADD ./target/Projet-Domotique-API-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]