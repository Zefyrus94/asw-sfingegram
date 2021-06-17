#!/bin/bash

docker -H kube-2 image rm  sfingegram/enigmi-service-v3
docker -H kubegram-2 image rm  sfingegram/connessioni-service-v3
#docker -H kube-2 image rm  sfingegram/enigmi-seguiti-service
#docker -H kube-2 image rm  sfingegram/api-gateway-service
#docker -H kube-2 image rm  postgres

docker -H kube-3 image rm  sfingegram/enigmi-service-v3
docker -H kubegram-3 image rm  sfingegram/connessioni-service-v3
#docker -H kube-3 image rm  sfingegram/enigmi-seguiti-service
#docker -H kube-3 image rm  sfingegram/api-gateway-service
#docker -H kube-3 image rm  postgres
