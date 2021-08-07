# SFINGEGRAM

Project of the Software Analysis and Design course for the academic year 2020-2021

## Description of this project

This project contains the code of *Sfingegram*, a simple social network for sharing puzzle games

System users can publish their puzzles.
They can also follow the puzzles of specific authors or specific types.
When a user gets to the page of the puzzles they follow, they're shown the puzzles of the authors and types he follows.

The original application *Sfingegram* consists of the following microservices, each running in its own docker container:

* The service *enigmi* manages the puzzles.
  Every puzzle has an author, a type, a title, a text (which can consist of multiple lines) and a solution (which can consist of multiple words).
  Operations:
  * `POST /enigmi` adds a new puzzle (given author, type, title, text and solution)
  * `GET /enigmi/{id}` finds a puzzle, given the id
  * `GET /enigmi/{id}/soluzione` finds the solution for a puzzle, given the id
  * `GET /enigmi` finds all the puzzles (without the solution)
  * `GET /cercaenigmi/autore/{autore}` finds all the puzzles of an author (without solution)
  * `GET //cercaenigmi/autori/{elenco-di-autori}` finds all the puzzles of a set of authors (without solution)
  * `GET /cercaenigmi/tipo/{tipo}` finds all the puzzles of a type (without solution)
  * `GET /cercaenigmi/tipi/{elenco-di-tipi}` finds all the puzzles of a set of types (without solution)

* The service *connessioni* manages the user connections, that is the authors and types followed by the users
  Connections are user-author or user-type pairs, where the authors are in general other system users
  Operations:
  * `POST /connessioniconautori` adds a new connection user-author (given user and author)
  * `GET /connessioniconautori` finds all the connections user-author
  * `GET /connessioniconautori/{utente}` finds all the connections user-author of a given user
  * `POST /connessionicontipi` adds a new connection user-type (given user and type)
  * `GET /connessionicontipi` finds all the connections user-type
  * `GET /connessionicontipi/{utente}` finds all the connections user-type of a given user

* The service *enigmi-seguiti* allows a user to find the puzzles of all the authors/types he follows.
  Operations:
  * `GET /enigmiseguiti/{utente}` finds all the puzzles followed by an author, that is the puzzles written by authors followed by that user or the puzzles having the type of the ones followed by that user (the puzzles are without solution)

* The service *api-gateway* (exposed on port *8080*) is the API gateway of the application that:
  * exposes the service *enigmi* on path `/enigmi` - e.g. `GET /enigmi/enigmi`
  * exposes the service *connessioni* on path `/connessioni` - e.g. `GET /connessioni/connessioniconautori/{utente}`
  * exposes the service *enigmi-seguiti* on path `/enigmi-seguiti` - e.g. `GET /enigmi-seguiti/enigmiseguiti/{utente}`

Following the logic described in the [delivery](http://cabibbo.inf.uniroma3.it/asw/progetti/asw-progetto-2021.pdf) we added the following services:

* The service *consul*, running in a docker container.

* 3 independent _PostgresSQL_ data bases, each running in a docker container. Information about *enigmi*, *enigmi-seguiti* and *connessioni* are stored in the data bases.
  * database *enigmi* consists of just one table with information about puzzles.
  * database *connessioni* consists of 2 tables relative to the connections user-author and user-type.
  * database *enigmi-seguiti* consists of 4 tables, 3 of which are copies of the tables *enigmi* and *connessioni* and another one with all the information to answer a request to 'GET /enigmiseguiti/{utente}' without the need to query the other tables and services.

* We added the services *Zookeeper* and *Kafka* for the asynchronous communication and manage the events produced by *enigmi* and *connessioni* and consumed by *enigmi-seguiti*.
  * In particular, we added the events *EnigmaCreatedEvent*, *ConnessioneConAutoreCreatedEvent*, *ConnessioneConTipoCreatedEvent*. We use a channel for the service enigmi and another one for the service connessioni. When the two serviceces generate an event they write it to the dedicated channel and the service *enigmi-seguiti* read and manages it as a subscriber of both these channels.

## Execution
(the following scripts need administrator permissions to be run)

To run this project:

* install and run docker (https://www.docker.com/)

* run the script `run-sfingegram.sh`

* to initialize the data tables, run the scripts `do-init-enigmi.sh` and `do-init-connessioni.sh` (when the application is up)

Some sample scripts are also provided

* the script `run-curl-client.sh` runs some example queries

* the script `do-get-enigmi.sh Nome_Autore` finds all the puzzles of an author

* the script `do-get-enigma.sh` find a puzzle

* the script `do-get-enigmi-di-autore.sh` finds all the puzzles of an author

* the script `do-get-enigmi-di-autori.sh` finds all the puzzles of a set of authors 

* the script `do-get-enigmi-di-tipo.sh` finds all the puzzles of a type 

* the script `do-get-enigmi-di-tipi.sh` finds all the puzzles of a set of types

* the script `do-get-connessioni.sh` finds all the connections

* the script `do-get-enigmi-seguiti.sh` finds all the puzzles followed by an author

Moreover:

* the script `do-post-altri-enigmi.sh` adds new puzzles

* the script `do-post-altre-connessioni.sh` adds new connections

In order to scale, run the script `run-sfingegram-replicas.sh` (it's possible to change the number of replicas at will through the option "--scale")

Finally, the application can be stopped through the script `stop-sfingegram.sh`
