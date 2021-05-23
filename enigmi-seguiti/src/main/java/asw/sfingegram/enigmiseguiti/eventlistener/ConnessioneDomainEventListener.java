package asw.sfingegram.enigmiseguiti.eventlistener;

import asw.sfingegram.common.api.event.DomainEvent; 
import asw.sfingegram.connessioniservice.api.event.*; 

//import asw.sfingegram.enigmiservice.client.domain.EnigmaDomainEventConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class ConnessioneDomainEventListener {

    private final Logger logger = Logger.getLogger(ConnessioneDomainEventListener.class.toString());

    @Autowired
    private ConnessioneDomainEventConsumer connessioneDomainEventConsumer;

	@KafkaListener(topics = ConnessioneConAutoreServiceEventChannel.channel)
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
        connessioneDomainEventConsumer.onEvent(event); 
    }
	@KafkaListener(topics = ConnessioneConTipoServiceEventChannel.channel)
    public void listenTipo(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
        connessioneDomainEventConsumer.onEvent(event); 
    }

}
