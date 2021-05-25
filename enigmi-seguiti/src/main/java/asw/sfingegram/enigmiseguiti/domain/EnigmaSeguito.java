package asw.sfingegram.enigmiseguiti.domain;

import javax.persistence.*; 

import lombok.*; 

/* Enigma, in formato completo. */ 
@Entity 
@IdClass(EnigmaSeguitoKey.class)
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "enigmiseguiti")
public class EnigmaSeguito{

	@Id
	@EqualsAndHashCode.Include
	private Long id; 

	@Id
	@EqualsAndHashCode.Include
	private String utente; 

	private String autore;
	private String tipo; 
	private String titolo; 
	private String testo; 
	
	public EnigmaSeguito(Long id, String autore, String tipo, String titolo, String testo, String utente) {
		this(); 
		this.id = id; 
		this.autore = autore; 
		this.tipo = tipo; 
		this.titolo = titolo; 
		this.testo = testo; 
		this.utente = utente; 
	}

	public EnigmaSeguito(Enigma enigma, String utente) {
		this(); 
		this.id = enigma.getId(); 
		this.autore = enigma.getAutore(); 
		this.tipo = enigma.getTipo(); 
		this.titolo = enigma.getTitolo(); 
		this.testo = enigma.getTesto(); 
		this.utente = utente; 
	}

	
}
