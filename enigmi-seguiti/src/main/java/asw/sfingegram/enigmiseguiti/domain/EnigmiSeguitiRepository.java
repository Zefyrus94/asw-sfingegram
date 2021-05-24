package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*; 

public interface EnigmiSeguitiRepository extends CrudRepository<EnigmaSeguito, Long> {

	public Collection<EnigmaSeguito> findAll();

	public Collection<EnigmaSeguito> findByUtente(String utente);

}

