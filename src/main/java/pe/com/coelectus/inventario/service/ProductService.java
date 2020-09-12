package pe.com.coelectus.inventario.service;

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
import pe.com.coelectus.inventario.entity.Measure;
import pe.com.coelectus.inventario.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	MeasureDao measureDao;
	
	public List<Product> findAll() {
		return productDao.findAll();
	}
	
	public List<Product> searchByName(String name) {
		return productDao.searchByName(name);
	}
	
	public Product findById(Long id) {
		return productDao.findById(id).orElse(null);
	}
	
	
	public Product save(String request) {
		Product product;

		JsonNode root;
		String name = null;
		Integer measureId = null;

		try {
			root = new ObjectMapper().readTree(request);
			
			name = root.path("name").asText();
			measureId = root.path("measureId").asInt();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		product = new Product();
		
		Measure measure = measureDao.findById(measureId).orElse(null);
		
		if(measure == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unidad de medida desconocida");
		}

		product.setName(name);
		product.setMeasure(measure);

		product = productDao.save(product);

		return product;
	}
	
	public Product update(String request) {
		Product product;

		JsonNode root;
		Long productId = null;
		String name = null;
		Integer measureId = null;

		try {
			root = new ObjectMapper().readTree(request);

			productId = root.path("id").asLong();
			name = root.path("name").asText();
			measureId = root.path("measureId").asInt();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		product = new Product();
		
		Measure measure = measureDao.findById(measureId).orElse(null);
		
		if(measure == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unidad de medida desconocida");
		}

		product.setProductId(productId);
		product.setName(name);
		product.setMeasure(measure);

		product = productDao.save(product);

		return product;
	}

}

