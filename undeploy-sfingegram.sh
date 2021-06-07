#!/bin/bash

echo 'Halting sfingegram' 

kubectl delete -f sfingegram-application.yaml -n sfingegram
kubectl delete namespace sfingegram

