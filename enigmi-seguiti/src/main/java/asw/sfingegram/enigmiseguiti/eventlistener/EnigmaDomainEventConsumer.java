package asw.sfingegram.enigmiseguiti.eventlistener;
import asw.sfingegram.common.api.event.DomainEvent; 
import asw.sfingegram.enigmaservice.api.event.*; 
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
public class EnigmaDomainEventConsumer {
	@Autowired
	private EnigmiRepository enigmiRepository;
	
	private final Logger logger = Logger.getLogger(EnigmaDomainEventConsumer.class.toString());

	public void onEvent(DomainEvent event) {
		if (event.getClass().equals(EnigmaCreatedEvent.class)) {
			EnigmaCreatedEvent ece = (EnigmaCreatedEvent) event;
			enigmaCreated(ece); 
			logger.info("ESTRAGGO L'EVENTO: " + event);
		}
		else {
			logger.info("UNKNOWN EVENT: " + event);			
		}
	}
	//salva sul database l'enigma che hai pescato dal canale kafka
	private void enigmaCreated(EnigmaCreatedEvent event) {
		Enigma enigma = new Enigma(event.getAutore(), event.getTipo(), event.getTitolo(),event.getTesto());
		enigmiRepository.save(enigma);
		logger.info("CREATED ENIGMA: " + enigma);
	}
}
