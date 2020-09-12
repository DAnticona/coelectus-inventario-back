package pe.com.coelectus.inventario.dto;

public class SaleDetailDto {
	
	private Long saleId;
	private Integer item;
	private StockDto stock;
	private Double quantity;
	private Double unitPrice;
	private Double totalPrice;
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public StockDto getStock() {
		return stock;
	}
	public void setStock(StockDto stock) {
		this.stock = stock;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "SaleDetailDto [saleId=" + saleId + ", item=" + item + ", stock=" + stock + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + "]";
	}
}
