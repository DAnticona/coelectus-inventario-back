package pe.com.coelectus.inventario.dto;

public class StockDto {

	private Long stockId;
	private Long productId;
	private Long date;
	private Double quantity;
	private Double unitCost;
	private Double unitPrice;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "StockDto [stockId=" + stockId + ", productId=" + productId + ", date=" + date + ", quantity=" + quantity
				+ ", unitCost=" + unitCost + ", unitPrice=" + unitPrice + "]";
	}
}
