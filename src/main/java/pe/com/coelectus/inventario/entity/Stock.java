package pe.com.coelectus.inventario.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import pe.com.coelectus.inventario.entity.id.StockId;

@Entity
@Table(name = "stock")
public class Stock {

	@EmbeddedId
	private StockId stockId;

	@MapsId("productId")
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	@JsonBackReference
	private Product product;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "unit_cost")
	private Double unitCost;

	@Column(name = "unit_price")
	private Double unitPrice;

	public StockId getStockId() {
		return stockId;
	}

	public void setStockId(StockId stockId) {
		this.stockId = stockId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
		return "Stock [stockId=" + stockId + ", product=" + product + ", date=" + date + ", quantity=" + quantity
				+ ", unitCost=" + unitCost + ", unitPrice=" + unitPrice + "]";
	}

}
