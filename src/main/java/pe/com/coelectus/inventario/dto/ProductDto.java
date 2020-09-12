package pe.com.coelectus.inventario.dto;

import java.util.List;

public class ProductDto {

	private Long productId;
	private String name;
	private MeasureDto measure;
	private List<StockDto> stocks;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MeasureDto getMeasure() {
		return measure;
	}
	public void setMeasure(MeasureDto measure) {
		this.measure = measure;
	}
	public List<StockDto> getStocks() {
		return stocks;
	}
	public void setStocks(List<StockDto> stocks) {
		this.stocks = stocks;
	}
	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", name=" + name + ", measure=" + measure + "]";
	}
}
