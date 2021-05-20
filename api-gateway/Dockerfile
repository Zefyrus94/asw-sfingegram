###########################################################
# Dockerfile per il servizio api-gateway
###########################################################

FROM openjdk:11-jdk 

ADD build/libs/api-gateway.jar api-gateway.jar

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "api-gateway.jar"]