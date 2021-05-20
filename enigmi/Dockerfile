###########################################################
# Dockerfile per il servizio degli enigmi
###########################################################

FROM openjdk:11-jdk 

ADD build/libs/enigmi.jar enigmi.jar

ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "enigmi.jar"]