###########################################################
# Dockerfile per il servizio delle connessioni
###########################################################

FROM openjdk:11-jdk 

ADD build/libs/connessioni.jar connessioni.jar

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "connessioni.jar"]