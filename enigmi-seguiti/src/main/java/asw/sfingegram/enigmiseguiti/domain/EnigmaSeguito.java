package asw.sfingegram.enigmiseguiti.domain;

import javax.persistence.*; 

import lombok.*; 

/* Enigma, in formato completo. */ 
@Entity 
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "enigmiseguiti")
public class EnigmaSeguito implements Comparable<EnigmaSeguito> {

	// @Id 
	// @EqualsAndHashCode.Include
	// private Long id; 

	// @Id
	// @EqualsAndHashCode.Include
	// private String utente; 

	@Id
	@EqualsAndHashCode.Include
	private String idutente; 

	private String autore;
	private String tipo; 
	private String titolo; 
	private String testo; 
	private String utente; 
	
	public EnigmaSeguito(Long id, String autore, String tipo, String titolo, String testo, String utente) {
		this(); 
		this.idutente = id+utente; 
		this.autore = autore; 
		this.tipo = tipo; 
		this.titolo = titolo; 
		this.testo = testo; 
		this.utente = utente; 
	}

	public EnigmaSeguito(Enigma enigma, String utente) {
		this(); 
		this.idutente = enigma.getId()+utente; 
		this.autore = enigma.getAutore(); 
		this.tipo = enigma.getTipo(); 
		this.titolo = enigma.getTitolo(); 
		this.testo = enigma.getTesto(); 
		this.utente = utente; 
	}

	@Override
	public int compareTo(EnigmaSeguito other) {
		return this.idutente.compareTo(other.idutente); 
	}
	
}
