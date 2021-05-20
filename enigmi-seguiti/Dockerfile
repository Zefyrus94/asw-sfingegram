###########################################################
# Dockerfile per il servizio degli enigmi-seguiti
###########################################################

FROM openjdk:11-jdk 

ADD build/libs/enigmi-seguiti.jar enigmi-seguiti.jar

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "enigmi-seguiti.jar"]