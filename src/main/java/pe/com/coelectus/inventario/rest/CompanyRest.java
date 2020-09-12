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

import pe.com.coelectus.inventario.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyRest {
	
	@Autowired
	CompanyService companyService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(companyService.findAll());
	}
	
	@GetMapping("{nudoc}")
	public ResponseEntity<?> findByDocumentNu(@PathVariable String nudoc) {
		return ResponseEntity.ok(companyService.findByDocumentNu(nudoc));
	}
	
	@GetMapping("search/{term}")
	public ResponseEntity<?> searchByName(@PathVariable String term) {
		return ResponseEntity.ok(companyService.searchByName(term));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {
		return ResponseEntity.ok(companyService.save(request));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody String request) {
		return ResponseEntity.ok(companyService.update(request));
	}
}
