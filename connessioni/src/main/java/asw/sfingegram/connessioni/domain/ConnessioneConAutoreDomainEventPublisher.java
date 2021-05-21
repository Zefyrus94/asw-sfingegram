package asw.sfingegram.connessioni.domain;
import asw.sfingegram.common.api.event.DomainEvent;

public interface ConnessioneConAutoreDomainEventPublisher {

    public void publish(DomainEvent event);

}