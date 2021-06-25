#!/bin/bash
echo Interrompo eventuali container eseguiti con docker-compose up...
echo
docker-compose down
echo Cancello le immagini dei servizi...
echo
DOCKER_IMAGE_IDS=$(docker images | grep ".*service")
if [ -z "$DOCKER_IMAGE_IDS" -o "$DOCKER_IMAGE_IDS" == " " ]; then
	echo "---- No images available for deletion ----"
	echo
else
	docker rmi -f $DOCKER_IMAGE_IDS
fi
echo Ripulisco i volumi inutilizzati...
echo
docker volume prune -f
echo Faccio la build
echo
gradle build
echo Creo e avvio i contenitori per il servizio sfingegram in modalità detached...
echo
docker-compose up -d