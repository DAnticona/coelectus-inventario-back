package pe.com.coelectus.inventario.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.ProductDto;
import pe.com.coelectus.inventario.entity.Product;

@Component
public class ProductConverter extends AbstractConverter<Product, ProductDto>{
	
	@Autowired
	StockConverter stockConverter;
	
	@Autowired
	MeasureConverter measureConverter;

	@Override
	public Product fromDto(ProductDto dto) {
		Product product = new Product();
		product.setProductId(dto.getProductId());
		product.setName(dto.getName());
		
		if(dto.getMeasure() != null) {
			product.setMeasure(measureConverter.fromDto(dto.getMeasure()));
		}
		
		if(dto.getStocks() != null) {
			product.setStocks(stockConverter.fromDto(dto.getStocks()));
		}
		
		return product;
	}

	@Override
	public ProductDto fromEntity(Product entity) {
		ProductDto product = new ProductDto();
		product.setProductId(entity.getProductId());
		product.setName(entity.getName());
		
		if(entity.getMeasure() != null) {
			product.setMeasure(measureConverter.fromEntity(entity.getMeasure()));
		}
		
		if(entity.getStocks() != null) {
			product.setStocks(stockConverter.fromEntity(entity.getStocks()));
		}
		
		return product;
	}

}
