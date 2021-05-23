#!/bin/bash
echo Mi collego al container postgres_enigmiseguiti
if uname -a | grep -i "MINGW" > /dev/null; then
	winpty docker exec -it postgres_enigmiseguiti psql --username=postgres --host=postgres_enigmiseguiti --dbname=sfingegram-enigmiseguiti
else
	sudo docker exec -it postgres_enigmiseguiti psql --username=postgres --host=postgres_enigmiseguiti --dbname=sfingegram-enigmiseguiti
fi