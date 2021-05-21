package asw.sfingegram.connessioni.eventpublisher;

import asw.sfingegram.common.api.event.DomainEvent;
import asw.sfingegram.connessioniservice.api.event.ConnessioneConTipoServiceChannel;
import asw.sfingegram.connessioni.domain.ConnessioneConTipoDomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component
public class ConnessioniConTipoDomainEventPublisherImpl implements ConnessioneConTipoDomainEventPublisher {

    private final Logger logger = Logger.getLogger(ConnessioniDomainEventPublisherImpl.class.toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    private String channel = ConnessioneConTipoServiceChannel.channel;

    @Override
    public void publish(DomainEvent event) {
        logger.info("PUBLISHING EVENT: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
        // template.flush();
    }
}