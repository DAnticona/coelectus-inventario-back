package pe.com.coelectus.inventario.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.dto.PersonDto;
import pe.com.coelectus.inventario.dto.converter.PersonConverter;
import pe.com.coelectus.inventario.entity.Person;
import pe.com.coelectus.inventario.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonRest {
	
	@Autowired
	PersonService personService;
	@Autowired
	PersonConverter personConverter;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(personService.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Person person = new Person();
		person.setPersonId(id);
		
		PersonDto personDto = personConverter.fromEntity(personService.findById(person));
		
		return ResponseEntity.ok(personDto);
	}
	
	@GetMapping("document/{nudoc}")
	public ResponseEntity<?> findUserById(@PathVariable String nudoc) {
		Person person = new Person();
		person.setDocumentNu(nudoc);
		
		PersonDto personDto = personConverter.fromEntity(personService.findByDocumentNu(nudoc));
		
		return ResponseEntity.ok(personDto);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {
		
		PersonDto person = personConverter.fromEntity(personService.save(request));
		
		return ResponseEntity.ok(person);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody String request) {
		
		PersonDto person = personConverter.fromEntity(personService.update(request));
		
		return ResponseEntity.ok(person);
	}
}
