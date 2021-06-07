#!/bin/bash

docker -H kube-2 image rm  sfingegram/enigmi-service
docker -H kube-2 image rm  sfingegram/connessioni-service
docker -H kube-2 image rm  sfingegram/enigmi-seguiti-service
docker -H kube-2 image rm  sfingegram/api-gateway-service
#docker -H kube-2 image rm  postgres

docker -H kube-3 image rm  sfingegram/enigmi-service
docker -H kube-3 image rm  sfingegram/connessioni-service
docker -H kube-3 image rm  sfingegram/enigmi-seguiti-service
docker -H kube-3 image rm  sfingegram/api-gateway-service
#docker -H kube-3 image rm  postgres