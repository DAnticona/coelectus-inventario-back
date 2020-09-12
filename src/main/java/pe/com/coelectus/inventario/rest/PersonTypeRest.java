package pe.com.coelectus.inventario.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.dto.PersonTypeDto;
import pe.com.coelectus.inventario.dto.converter.PersonTypeConverter;
import pe.com.coelectus.inventario.service.PersonTypeService;

@RestController
@RequestMapping("/person-type")
public class PersonTypeRest {
	
	@Autowired
	PersonTypeService typeService;
	@Autowired
	PersonTypeConverter typeConverter;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<PersonTypeDto> types = typeConverter.fromEntity(typeService.findAll());
		return ResponseEntity.ok(types);
	}

}
