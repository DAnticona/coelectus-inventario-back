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

import pe.com.coelectus.inventario.dto.MeasureDto;
import pe.com.coelectus.inventario.dto.converter.MeasureConverter;
import pe.com.coelectus.inventario.service.MeasureService;

@RestController
@RequestMapping("/measures")
public class MeasureRest {
	
	@Autowired
	MeasureService measureService;
	@Autowired
	MeasureConverter measureConverter; 
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(measureService.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findAll(@PathVariable Integer id) {
		MeasureDto measureDto = measureConverter.fromEntity(measureService.findById(id));
		
		return ResponseEntity.ok(measureDto);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {
		
		MeasureDto measure = measureConverter.fromEntity(measureService.update(request));
		
		return ResponseEntity.ok(measure);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody String request) {
		
		MeasureDto measure = measureConverter.fromEntity(measureService.update(request));
		
		return ResponseEntity.ok(measure);
	}
}
