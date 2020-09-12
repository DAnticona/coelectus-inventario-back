package pe.com.coelectus.inventario.dto;

public class ClientDto {
	
	private Long clientId;
	private PersonTypeDto type;
	private PersonDto person;
	private CompanyDto company;
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public PersonTypeDto getType() {
		return type;
	}
	public void setType(PersonTypeDto type) {
		this.type = type;
	}
	public PersonDto getPerson() {
		return person;
	}
	public void setPerson(PersonDto person) {
		this.person = person;
	}
	public CompanyDto getCompany() {
		return company;
	}
	public void setCompany(CompanyDto company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "ClientDto [clientId=" + clientId + ", type=" + type + ", person=" + person + ", company=" + company + "]";
	}
}
