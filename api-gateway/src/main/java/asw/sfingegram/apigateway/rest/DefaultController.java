package asw.instagnam.apigateway.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*; 

@RestController
public class DefaultController {

	@GetMapping("/")
	public String index() {
		return "Benvenuto nell'app sfingegram, gli endpoint sono /enigmi, /connessioni, /enigmiseguiti";
	}
	
}
