#!/bin/bash

# trova tutti gli enigmi 

echo "# trova tutti gli enigmi" 
echo $(curl -s sfingegram:31080/enigmi/enigmi)
echo 

