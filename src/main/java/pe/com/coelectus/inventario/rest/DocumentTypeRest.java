package pe.com.coelectus.inventario.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.dto.DocumentTypeDto;
import pe.com.coelectus.inventario.dto.converter.DocumentTypeConverter;
import pe.com.coelectus.inventario.service.DocumentTypeService;

@RestController
@RequestMapping("/document-type")
public class DocumentTypeRest {

	@Autowired
	DocumentTypeService docTypeService;
	@Autowired
	DocumentTypeConverter docTypeConverter;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<DocumentTypeDto> docTypes = docTypeConverter.fromEntity(docTypeService.findAll());
		return ResponseEntity.ok(docTypes);
	}
}
