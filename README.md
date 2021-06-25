# SFINGEGRAM

Progetto del corso di Analisi e progettazione del software per l'anno accademico 2020-2021.

## Descrizione dell'applicazione

Questo progetto contiene il il codice di *Sfingegram*, un semplice social network per la condivisione di enigmi (ovvero, giochi enigmistici).
Gli utenti del sistema possono pubblicare degli enigmi.
Possono poi seguire gli enigmi di specifici autori o di specifici tipi.  
Quando un utente accede alla pagina degli enigmi che segue, gli vengono mostrati gli enigmi degli autori e dei tipi che segue.

L'applicazione originale *Sfingegram* è composta dai seguenti microservizi:

* Il servizio *enigmi* gestisce gli enigmi.
  Ogni enigma ha un autore, un tipo, un titolo, un testo (che può essere composta da più righe) e una soluzione (che può essere composta da più parole).
  Operazioni:
  * `POST /enigmi` aggiunge un nuovo enigma (dati autore, tipo, titolo, testo e soluzione)
  * `GET /enigmi/{id}` trova un enigma, dato l'id
  * `GET /enigmi/{id}/soluzione` trova la soluzione di un enigma, dato l'id
  * `GET /enigmi` trova tutti gli enigmi (senza la soluzione)
  * `GET /cercaenigmi/autore/{autore}` trova tutti gli enigmi di un certo autore (senza soluzione)
  * `GET //cercaenigmi/autori/{elenco-di-autori}` trova tutti gli enigmi di un insieme di autori (senza soluzione)
  * `GET /cercaenigmi/tipo/{tipo}` trova tutti gli enigmi di un certo tipo (senza soluzione)
  * `GET /cercaenigmi/tipi/{elenco-di-tipi}` trova tutti gli enigmi di un insieme di tipi (senza soluzione)
  
* Il servizio *connessioni* gestisce le connessioni degli utenti, ovvero gli autori e i tipi di enigmi seguiti dagli utenti.
  Le connessioni sono delle coppie utente-autore oppure utente-tipo, in cui gli autori sono in genere altri utenti del sistema.
  Operazioni:
  * `POST /connessioniconautori` aggiunge una nuova connessione utente-autore (dati utente e autore)
  * `GET /connessioniconautori` trova tutte le connessioni utente-autore
  * `GET /connessioniconautori/{utente}` trova tutte le connessioni utente-autore di un certo utente
  * `POST /connessionicontipi` aggiunge una nuova connessione utente-tipo (dati utente e tipo)
  * `GET /connessionicontipi` trova tutte le connessioni utente-tipo
  * `GET /connessionicontipi/{utente}` trova tutte le connessioni utente-tipo di un certo utente

* Il servizio *enigmi-seguiti* consente a un utente di trovare gli enigmi di tutti gli autori e di tutti i tipi che segue.
  Operazioni:
  * `GET /enigmiseguiti/{utente}` trova tutti gli enigmi seguiti da un certo utente, ovvero gli enigmi scritti da autori seguiti da quell'utente o di tipi di enigmi seguiti da quell'utente (gli enigmi sono senza soluzione)
  
* Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *enigmi* sul path `/enigmi` - ad esempio, `GET /enigmi/enigmi`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioniconautori/{utente}`
  * espone il servizio *enigmi-seguiti* sul path `/enigmi-seguiti` - ad esempio, `GET /enigmi-seguiti/enigmiseguiti/{utente}`

Seguendo la logica descritta nella consegna (disponibile al link: [consegna](http://cabibbo.inf.uniroma3.it/asw/progetti/asw-progetto-2021.pdf)) abbiamo aggiunto i seguenti servizi:

* Tre basi di dati _PostgresSQL_ indipendenti che girano su altrettanti container docker. Nelle tre basi di dati vengono salvate le informazioni relative a *enigmi*, *enigmi-seguiti* e *connessioni*.
  * nel database di *enigmi* è presente una sola tabella in cui vengono salvate le informazioni relativi agli enigmi.
  * nel database di *connessioni* sono presenti due tabelle in cui vengono salvate le connessioni tra utente e autore e tra utente e tipo.
  * nel database di *enigmi-seguiti* sono presenti quattro tabelle, tre di esse sono le replicazioni delle tre tabelle di *enigmi* e *connessioni* mentre la quarta ha tutte le informazioni necessarie per rispondere direttamente ad una richiesta api 'GET /enigmiseguiti/{utente}' senza la necessità di interrogare le altre tabelle o servizi.

* Abbiamo aggiunto i servizi di *Zookeeper*  e *Kafka* per l'implementazione della comunicazione _asincrona_ degli eventi prodotti da *enigmi* e *connessioni* verso *enigmi-seguiti*.
  * In particolare, abbiamo aggiunto gli eventi *EnigmaCreatedEvent*, *ConnessioneConAutoreCreatedEvent*, *ConnessioneConTipoCreatedEvent*. Abbiamo creato un canale per il servizio enigmi e uno per il servizio connessioni. Quando i due servizi generano un evento lo trasmettono nel canale dedicato e il servizio *enigmi-seguiti* li cattura essendo iscritto ad entrambi questi canali.

## Descrizione dell'architettura

* Rispetto all'implementazione dell'applicazione utilizzando docker compose abbiamo eliminato la dipendenza da consul, lasciando a Kubernetes l'onere di gestire i servizi di loadbalacing e routing. Abbiamo di fatto quindi sostituito (nei file _build.gradle_ dei vari servizi) le dipendenze di spring cloud di consul con la dipendenza a kubernetes.
* L'ambiente di esecuzione della applicazione è l'ambiente descritto nella [repository ufficiale del corso](https://github.com/aswroma3/asw/tree/master/environments/kube-cluster), il quale utilizza Vagrant e VirtualBox. Nella cartella *kube-cluster* è presente il vagrantfile dell'ambiente. E' composto da quattro nodi, un nodo dev, un nodo master e due nodi worker:
  1. un nodo master con 2 giga di ram e due cpu;
  2. due nodi worker con ciascuno 4 giga di ram e due cpu;
  3. un nodo dev con 1 giga di ram e due cpu.

* Le build dei singoli servizi sono pushate su Docker Hub (necessario perché vengano utilizzate per la costruzione dei pod al momento dell'avvio del cluster tramite Kubernetes).
  * Le immagini possono essere pushate attraverso lo script: `build-and-push-docker-images.sh`.

## Esecuzione

Per eseguire questo progetto:

* eseguire il comando `vagrant up` dalla cartella kube-cluster.

* eseguire lo script `deploy-sfingegram.sh` sul nodo dev.

* per inizializzare le basi di dati con dei dati di esempio, eseguire gli script `do-init-enigmi.sh` e `do-init-connessioni.sh` dal nodo dev.

Sono anche forniti alcuni script di esempio:

* lo script `run-curl-client.sh` esegue un insieme di interrogazioni di esempio

* lo script `do-get-enigmi.sh` trova tutti gli enigmi

* lo script `do-get-enigma.sh` trova un enigma

* lo script `do-get-enigmi-di-autore.sh` trova tutti gli enigmi di un certo autore

* lo script `do-get-enigmi-di-autori.sh` trova tutti gli enigmi di un insieme di autori  

* lo script `do-get-enigmi-di-tipo.sh` trova tutti gli enigmi di un certo tipo  

* lo script `do-get-enigmi-di-tipi.sh` trova tutti gli enigmi di un insieme di tipi  

* lo script `do-get-connessioni.sh` trova tutte le connessioni

* lo script `do-get-enigmi-seguiti.sh` trova tutti gli enigmi seguiti da un certo utente

Ed inoltre:

* lo script `do-post-altri-enigmi.sh` aggiunge nuovi enigmi

* lo script `do-post-altre-connessioni.sh` aggiunge nuove connessioni

Alla fine, l'applicazione può essere arrestata usando lo script `undeploy-sfingegram.sh` in dev.