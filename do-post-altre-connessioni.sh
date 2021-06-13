#!/bin/bash

# aggiunge nuove connessioni 

curl -X POST "http://sfingegram:31080/connessioni/connessioniconautori" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Barbara\", \"autore\": \"Donatella\"}"
echo 

curl -X POST "http://sfingegram:31080/connessioni/connessionicontipi" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Alice\", \"tipo\": \"Indovinello\"}"
echo 
