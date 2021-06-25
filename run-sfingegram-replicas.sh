#!/bin/bash
docker-compose up -d --scale kafka=2 --scale enigmi=2 --scale connessioni=2 --scale enigmi-seguiti=3