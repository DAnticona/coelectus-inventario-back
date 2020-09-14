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

import pe.com.coelectus.inventario.dto.ApiResponse;
import pe.com.coelectus.inventario.dto.ProductDto;
import pe.com.coelectus.inventario.dto.converter.ProductConverter;
import pe.com.coelectus.inventario.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRest {
	
	@Autowired
	ProductService productService;
	@Autowired
	ProductConverter productConverter; 
	
	@GetMapping("/slice/{page}")
	public ResponseEntity<?> findAll(@PathVariable Integer page) {
		ApiResponse response = productService.findAll(page);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findAll(@PathVariable Long id) {
		ProductDto productDto = productConverter.fromEntity(productService.findById(id));
		
		return ResponseEntity.ok(productDto);
	}
	
	@GetMapping("search/{name}")
	public ResponseEntity<?> searchByName(@PathVariable String name) {
		List<ProductDto> products = productConverter.fromEntity(productService.searchByName(name));
		
		return ResponseEntity.ok(products);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {
		
		ProductDto product = productConverter.fromEntity(productService.update(request));
		
		return ResponseEntity.ok(product);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody String request) {
		
		ProductDto product = productConverter.fromEntity(productService.update(request));
		
		return ResponseEntity.ok(product);
	}

}
