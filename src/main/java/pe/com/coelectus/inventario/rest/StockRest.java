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

import pe.com.coelectus.inventario.dto.StockDto;
import pe.com.coelectus.inventario.dto.converter.StockConverter;
import pe.com.coelectus.inventario.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockRest {

	@Autowired
	StockService stockService;
	@Autowired
	StockConverter stockConverter;

	@GetMapping("/bigger")
	public ResponseEntity<?> findAll() {
		List<StockDto> stocks = stockConverter.fromEntity(stockService.findStocksBiggerThanZero());

		return ResponseEntity.ok(stocks);
	}

	@GetMapping("product/{productId}")
	public ResponseEntity<?> findByProduct(@PathVariable Long productId) {
		List<StockDto> stocks = stockConverter.fromEntity(stockService.findByProduct(productId));

		return ResponseEntity.ok(stocks);
	}

	@GetMapping("product/{productId}/stock/{stockId}")
	public ResponseEntity<?> findById(@PathVariable Long stockId, @PathVariable Long productId) {
		StockDto stock = stockConverter.fromEntity(stockService.findById(stockId, productId));

		return ResponseEntity.ok(stock);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody String request) {
		StockDto stock = stockConverter.fromEntity(stockService.save(request));

		return ResponseEntity.ok(stock);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody String request) {
		StockDto stock = stockConverter.fromEntity(stockService.update(request));

		return ResponseEntity.ok(stock);
	}

}
