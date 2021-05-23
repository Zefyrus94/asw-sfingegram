package asw.sfingegram.enigmiservice.client.domain;

import lombok.*; 

@Data 
@NoArgsConstructor @AllArgsConstructor
public class Enigma {

	private Long id; 
	private String autore; 
	private String tipo; 
	private String titolo; 
	private String/*[]*/ testo;
	
}
