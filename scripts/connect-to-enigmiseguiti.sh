#!/bin/bash
echo Mi collego al container postgres-enigmiseguiti
if uname -a | grep -i "MINGW" > /dev/null; then
	winpty docker exec -it postgres-enigmiseguiti psql --username=postgres --host=postgres-enigmiseguiti --dbname=sfingegram-enigmiseguiti
else
	sudo docker exec -it postgres-enigmiseguiti psql --username=postgres --host=postgres-enigmiseguiti --dbname=sfingegram-enigmiseguiti
fi