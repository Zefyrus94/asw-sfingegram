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
	@Autowired
	private EnigmiRepository enigmiRepository;
	@Autowired
	private EnigmiSeguitiRepository enigmiSeguitiRepository;
	
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

		Collection<Enigma> enigmiDiAutore =  enigmiRepository.findByAutore(event.getAutore());
		if(enigmiDiAutore.size()>0){
			List<EnigmaSeguito> enigmiSeguiti = new ArrayList<>();
			for (Enigma en : enigmiDiAutore) {

				EnigmaSeguito es = new EnigmaSeguito(en,event.getUtente());

				enigmiSeguiti.add(es);
			}
			logger.info("CREATED ENIGMI_SEGUITI TUPLES  from AUTORE: ");
			logger.info(enigmiSeguiti.toString());
			enigmiSeguitiRepository.saveAll(enigmiSeguiti);
		}
		logger.info("CREATED CONNESSIONE_AUTORE: " + connessione);
	}

	private void connessioneTipoCreated(ConnessioneConTipoCreatedEvent event) {
		ConnessioneConTipo connessione = new ConnessioneConTipo(event.getUtente(), event.getTipo());
		connessioniTipiRepository.save(connessione);

		Collection<Enigma> enigmiDiTipo =  enigmiRepository.findByTipo(event.getTipo());
		if(enigmiDiTipo.size()>0){
			List<EnigmaSeguito> enigmiSeguiti = new ArrayList<>();
			for (Enigma en : enigmiDiTipo) {

				EnigmaSeguito es = new EnigmaSeguito(en,event.getUtente());

				enigmiSeguiti.add(es);
			}
			logger.info("CREATED ENIGMI_SEGUITI TUPLES from TIPO: ");
			logger.info(enigmiSeguiti.toString());
			enigmiSeguitiRepository.saveAll(enigmiSeguiti);
		}
		logger.info("CREATED CONNESSIONE_AUTORE: " + connessione);

		logger.info("CREATED CONNESSIONE_TIPO: " + connessione);
	}
}
