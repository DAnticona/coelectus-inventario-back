package pe.com.coelectus.inventario.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.dto.ClientDto;
import pe.com.coelectus.inventario.dto.converter.ClientConverter;
import pe.com.coelectus.inventario.entity.Client;
import pe.com.coelectus.inventario.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientRest {

	@Autowired
	ClientService clientService;
	@Autowired
	ClientConverter clientConverter;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<ClientDto> clients = clientConverter.fromEntity(clientService.findAll());
		return ResponseEntity.ok(clients);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Client client = new Client();
		client.setClientId(id);

		ClientDto clientDto = clientConverter.fromEntity(clientService.findById(client.getClientId()));

		return ResponseEntity.ok(clientDto);
	}

	@GetMapping("search/{nudoc}")
	public ResponseEntity<?> searchByDocument(@PathVariable String nudoc) {
		
		Client client = clientService.findByDocumentNu(nudoc);
		
		if(client == null) {
			return ResponseEntity.ok(null);
		}
		
		ClientDto clientDto = clientConverter.fromEntity(client);
		
		return ResponseEntity.ok(clientDto);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {

		ClientDto client = clientConverter.fromEntity(clientService.save(request));

		return ResponseEntity.ok(client);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody String request) {

		ClientDto client = clientConverter.fromEntity(clientService.update(request));

		return ResponseEntity.ok(client);
	}

}
