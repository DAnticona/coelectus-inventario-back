package pe.com.coelectus.inventario.entity.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SaleDetailId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "sale_id")
	private Long saleId;

	@Column(name = "item")
	private Integer item;

	public SaleDetailId() {
	}

	public SaleDetailId(Long saleId, Integer item) {
		this.saleId = saleId;
		this.item = item;
	}

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

	@Override
	public String toString() {
		return "SaleDetailId [saleId=" + saleId + ", item=" + item + "]";
	}

}
