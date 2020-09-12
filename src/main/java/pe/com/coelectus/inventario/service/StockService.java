package pe.com.coelectus.inventario.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.MeasureDao;
import pe.com.coelectus.inventario.dao.ProductDao;
import pe.com.coelectus.inventario.dao.StockDao;
import pe.com.coelectus.inventario.entity.Product;
import pe.com.coelectus.inventario.entity.Stock;
import pe.com.coelectus.inventario.entity.id.StockId;

@Service
public class StockService {
	
	@Autowired
	StockDao stockDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	MeasureDao measureDao;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public List<Stock> findStocksBiggerThanZero() {
		return stockDao.findStocksBiggerThanZero();
	}
	
	public List<Stock> findByProduct(Long idProduct) {
		Product product =  productDao.findById(idProduct).orElse(null);
		
		return stockDao.findByProduct(product);
	}
	
	public Stock findById(Long stockId, Long productId) {
		
		Stock stock =  stockDao.findById(new StockId(stockId, productId)).orElse(null);
		
		if(stock == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock desconocido");
		}
		
		return stock;
	}
	
	public Stock save(String request) {
		
		Stock stock;

		JsonNode root;
		Long productId = null;
		LocalDate date = null;
		Double quantity = null;
		Double unitCost = null;
		Double unitPrice = null;

		try {
			root = new ObjectMapper().readTree(request);

			productId = root.path("productId").asLong();
			date = LocalDate.parse(root.path("date").asText(), formatter);
			quantity = root.path("quantity").asDouble();
			unitCost = root.path("unitCost").asDouble();
			unitPrice = root.path("unitPrice").asDouble();
			
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros inválidos");
		}
		
		stock = new Stock();
		Product product = productDao.findById(productId).orElse(null);
		
		if(product == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto desconocido");
		}
		
//		Measure measure = measureDao.findById(measureId).orElse(null);
//		
//		if(measure == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unidad de medida desconocida");
//		}
		
		stock.setStockId(new StockId(this.getNewId(productId), productId));
		stock.setProduct(product);
		stock.setDate(date);
		stock.setQuantity(quantity);
		stock.setUnitCost(unitCost);
		stock.setUnitPrice(unitPrice);

		stock = stockDao.save(stock);

		return stock;
		
	}
	
	public Stock update(String request) {
		
		Stock stock;

		JsonNode root;
		Long productId = null;
		Long stockId = null;
		LocalDate date = null;
		Double quantity = null;
		Double unitCost = null;
		Double unitPrice = null;

		try {
			root = new ObjectMapper().readTree(request);

			productId = root.path("productId").asLong();
			stockId = root.path("stockId").asLong();
			date = LocalDate.parse(root.path("date").asText(), formatter);
			quantity = root.path("quantity").asDouble();
			unitCost = root.path("unitCost").asDouble();
			unitPrice = root.path("unitPrice").asDouble();
			
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros inválidos");
		}
		
		stock = new Stock();
		Product product = productDao.findById(productId).orElse(null);
				
		if(product == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto desconocido");
		}
		
//		Measure measure = measureDao.findById(measureId).orElse(null);
//		
//		if(measure == null) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unidad de medida desconocida");
//		}
		
		stock.setStockId(new StockId(stockId, productId));
		stock.setProduct(product);
		stock.setDate(date);
		stock.setQuantity(quantity);
		stock.setUnitCost(unitCost);
		stock.setUnitPrice(unitPrice);

		stock = stockDao.save(stock);

		return stock;
		
	}
	
	private Long getNewId(Long productId) {
		return stockDao.getMaxId(productId) + 1L;
	}

}
