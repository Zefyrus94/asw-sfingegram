package asw.sfingegram.restaurantservice.client.restaurantservice.eventlistener;

import asw.sfingegram.common.api.event.DomainEvent; 
import asw.sfingegram.enigmaservice.api.event.*; 

import asw.sfingegram.enigmiservice.client.domain.EnigmaDomainEventConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component 
public class EnigmaDomainEventListener {

    private final Logger logger = Logger.getLogger(EnigmaDomainEventListener.class.toString());

    @Autowired
    private EnigmaDomainEventConsumer enigmaDomainEventConsumer;

	@KafkaListener(topics = EnigmaServiceEventChannel.channel)
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
		enigmaDomainEventConsumer.onEvent(event); 
    }

}
