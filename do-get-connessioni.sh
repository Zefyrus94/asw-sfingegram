#!/bin/bash

# trova tutte le connessioni 

echo "# tutte le connessioni con autori" 
echo $(curl -s sfinge:31080/connessioni/connessioniconautori)
echo 

echo "# tutte le connessioni con tipi" 
echo $(curl -s sfinge:31080/connessioni/connessionicontipi)
echo 

