package pe.com.coelectus.inventario.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import pe.com.coelectus.inventario.entity.id.SaleDetailId;

@Entity
@Table(name = "sale_detail")
public class SaleDetail {
	
	@EmbeddedId
	private SaleDetailId saleDetailId;
	
	@MapsId("saleId")
	@ManyToOne
	@JoinColumn(name = "sale_id", referencedColumnName = "sale_id")
	@JsonBackReference
	private Sale sale;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "product_id", referencedColumnName = "product_id"),
		@JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
	})
	private Stock stock;
	
	@Column(name = "quantity")
	private Double quantity;
	
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@Column(name = "service_fg")
	private String serviceFg;
	
	@Column(name = "service_name")
	private String serviceName;

	public SaleDetailId getSaleDetailId() {
		return saleDetailId;
	}

	public void setSaleDetailId(SaleDetailId saleDetailId) {
		this.saleDetailId = saleDetailId;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
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

	public String getServiceFg() {
		return serviceFg;
	}

	public void setServiceFg(String serviceFg) {
		this.serviceFg = serviceFg;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "SaleDetail [saleDetailId=" + saleDetailId + ", sale=" + sale + ", stock=" + stock + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", serviceFg=" + serviceFg
				+ ", serviceName=" + serviceName + "]";
	}
}
