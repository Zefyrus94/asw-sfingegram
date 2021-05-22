package asw.sfingegram.connessioni.domain;
import asw.sfingegram.common.api.event.DomainEvent;

public interface ConnessioneConTipoDomainEventPublisher {

    public void publish(DomainEvent event);

}