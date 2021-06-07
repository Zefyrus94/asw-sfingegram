#!/bin/bash

echo 'Starting sfingegram' 

kubectl create namespace sfingegram
kubectl create -f sfingegram-application.yaml -n sfingegram

# kubectl rollout status deployment/sentence -n sentence

