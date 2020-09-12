package pe.com.coelectus.inventario.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.ClientDto;
import pe.com.coelectus.inventario.entity.Client;

@Component
public class ClientConverter extends AbstractConverter<Client, ClientDto> {
	
	@Autowired
	PersonTypeConverter typeConverter;
	@Autowired
	PersonConverter personConverter;
	@Autowired
	CompanyConverter companyConverter;
	
	@Override
	public Client fromDto(ClientDto dto) {
		Client client = new Client();
		client.setClientId(dto.getClientId());
		
		if(dto.getType() != null) {
			client.setType(typeConverter.fromDto(dto.getType()));
		}
		
		if(dto.getPerson() != null) {
			client.setPerson(personConverter.fromDto(dto.getPerson()));
		}
		
		if(dto.getCompany() != null) {
			client.setCompany(companyConverter.fromDto(dto.getCompany()));
		}
		return client;
	}

	@Override
	public ClientDto fromEntity(Client entity) {
		ClientDto client = new ClientDto();
		client.setClientId(entity.getClientId());
		
		if(entity.getType() != null) {
			client.setType(typeConverter.fromEntity(entity.getType()));
		}
		
		if(entity.getPerson() != null) {
			client.setPerson(personConverter.fromEntity(entity.getPerson()));
		}
		
		if(entity.getCompany() != null) {
			client.setCompany(companyConverter.fromEntity(entity.getCompany()));
		}
		return client;
	}

}
