package asw.sfingegram.enigmiseguiti.eventlistener;
import asw.sfingegram.common.api.event.DomainEvent; 
import asw.sfingegram.enigmiseguiti.domain.*;
//connessioni
import asw.sfingegram.connessioniservice.api.event.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;//?
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;//?

import java.util.logging.Logger; 
import java.util.*; 
import java.util.stream.*; 

import java.util.logging.*;

@Service
public class ConnessioneDomainEventConsumer {
	@Autowired
	private ConnessioniConAutoriRepository connessioniAutoriRepository;
	@Autowired
	private ConnessioniConTipiRepository connessioniTipiRepository;
	
	
	private final Logger logger = Logger.getLogger(ConnessioneDomainEventConsumer.class.toString());

	public void onEvent(DomainEvent event) {
		if (event.getClass().equals(ConnessioneConAutoreCreatedEvent.class)) {
			ConnessioneConAutoreCreatedEvent cace = (ConnessioneConAutoreCreatedEvent) event;
			connessioneAutoreCreated(cace); 
			logger.info("ESTRAGGO L'EVENTO: " + event);
		}
		else if (event.getClass().equals(ConnessioneConTipoCreatedEvent.class)) {
			ConnessioneConTipoCreatedEvent ctce = (ConnessioneConTipoCreatedEvent) event;
			connessioneTipoCreated(ctce); 
			logger.info("ESTRAGGO L'EVENTO: " + event);
		}
		else {
			logger.info("UNKNOWN EVENT: " + event);			
		}
	}
	//salva sul database la connessione con tipo che hai pescato dal canale kafka
	private void connessioneAutoreCreated(ConnessioneConAutoreCreatedEvent event) {
		ConnessioneConAutore connessione = new ConnessioneConAutore(event.getUtente(), event.getAutore());
		connessioniAutoriRepository.save(connessione);
		logger.info("CREATED CONNESSIONE_AUTORE: " + connessione);
	}
	private void connessioneTipoCreated(ConnessioneConTipoCreatedEvent event) {
		ConnessioneConTipo connessione = new ConnessioneConTipo(event.getUtente(), event.getTipo());
		connessioniTipiRepository.save(connessione);
		logger.info("CREATED CONNESSIONE_TIPO: " + connessione);
	}
}
