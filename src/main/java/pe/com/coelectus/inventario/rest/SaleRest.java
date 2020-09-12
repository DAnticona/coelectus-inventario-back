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

import pe.com.coelectus.inventario.dto.SaleDto;
import pe.com.coelectus.inventario.dto.converter.SaleConverter;
import pe.com.coelectus.inventario.dto.converter.SaleDetailConverter;
import pe.com.coelectus.inventario.service.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleRest {
	
	@Autowired
	SaleService saleService;
	@Autowired
	SaleConverter saleConverter;
	@Autowired
	SaleDetailConverter detailConverter;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<SaleDto> sales = saleConverter.fromEntity(saleService.findAll());
		return ResponseEntity.ok(sales);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		SaleDto sale = saleConverter.fromEntity(saleService.findById(id));
		return ResponseEntity.ok(sale);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {
		
		SaleDto sale = saleConverter.fromEntity(saleService.save(request));

		return ResponseEntity.ok(sale);
		
	}
	
	@PutMapping("annull")
	public ResponseEntity<?> annull(@RequestBody String request) {
		
		SaleDto sale = saleConverter.fromEntity(saleService.annull(request));

		return ResponseEntity.ok(sale);
		
	}

}
