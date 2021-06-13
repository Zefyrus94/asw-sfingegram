#!/bin/bash

# trova tutti gli enigmi 

echo "# trova tutti gli enigmi" 
echo $(curl -s sfinge:31080/enigmi/enigmi)
echo 

