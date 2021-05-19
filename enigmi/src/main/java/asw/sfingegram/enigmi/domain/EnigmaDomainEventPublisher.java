package asw.sfingegram.enigmi.domain;
import asw.sfingegram.common.api.event.DomainEvent; 

public interface EnigmaDomainEventPublisher {

    public void publish(DomainEvent event);

}
