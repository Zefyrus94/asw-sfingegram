#!/bin/bash

# trova tutti gli enigmi di un insieme di tipi  

# i tipi devono essere separati da virgola 
# se un tipo contiene spazi deve essere racchiuso tra virgolette 
TIPI=$(echo $1 | sed -e "s/ /%20/g" | sed -e "s/,/%2C/g") 

echo "# tutti gli enigmi dei tipi $1" 
echo $(curl -s sfingegram:31080/enigmi/cercaenigmi/tipi/$TIPI)
echo 

