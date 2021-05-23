#!/bin/bash
echo Interrompo eventuali container eseguiti con docker-compose up...
docker-compose down
echo Cancello le immagini dei servizi...
DOCKER_IMAGE_IDS=$(docker images | grep ".*service")
if [ -z "$DOCKER_IMAGE_IDS" -o "$DOCKER_IMAGE_IDS" == " " ]; then
	echo "---- No images available for deletion ----"
else
	docker rmi -f $DOCKER_IMAGE_IDS
fi
echo Ripulisco i volumi inutilizzati...
docker volume prune -f
echo Faccio la build
gradle build
echo Creo e avvio i contenitori per il servizio sfingegram in modalità detached...
docker-compose up -d