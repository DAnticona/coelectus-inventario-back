package pe.com.coelectus.inventario.dto;

import java.util.List;

public class SaleDto {
	
	private Long saleId;
	private ClientDto client;
	private Long date;
	private Double amount;
	private String activeFg;
	private List<SaleDetailDto> details;
	
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	public ClientDto getClient() {
		return client;
	}
	public void setClient(ClientDto client) {
		this.client = client;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public List<SaleDetailDto> getDetails() {
		return details;
	}
	public void setDetails(List<SaleDetailDto> details) {
		this.details = details;
	}
	public String getActiveFg() {
		return activeFg;
	}
	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
	@Override
	public String toString() {
		return "SaleDto [saleId=" + saleId + ", client=" + client + ", date=" + date + ", amount=" + amount
				+ ", activeFg=" + activeFg + "]";
	}
}
