package pe.com.coelectus.inventario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.ClientDao;
import pe.com.coelectus.inventario.dao.CompanyDao;
import pe.com.coelectus.inventario.dao.DocumentTypeDao;
import pe.com.coelectus.inventario.dao.PersonDao;
import pe.com.coelectus.inventario.dao.PersonTypeDao;
import pe.com.coelectus.inventario.dto.ApiResponse;
import pe.com.coelectus.inventario.dto.ClientDto;
import pe.com.coelectus.inventario.dto.converter.ClientConverter;
import pe.com.coelectus.inventario.entity.Client;
import pe.com.coelectus.inventario.entity.Company;
import pe.com.coelectus.inventario.entity.DocumentType;
import pe.com.coelectus.inventario.entity.Person;
import pe.com.coelectus.inventario.entity.PersonType;

@Service
public class ClientService {

	@Autowired
	ClientDao clientDao;
	@Autowired
	PersonTypeDao typeDao;
	@Autowired
	PersonDao personDao;
	@Autowired
	CompanyDao companyDao;
	@Autowired
	DocumentTypeDao docTypeDao;
	@Autowired
	ClientConverter clientConverter;
	
	public List<Client> findAll() {
		return clientDao.findAll();
	}
	
	public Client findByDocumentNu(String documentNu) {
		Client client = null;
		
		Company company = companyDao.findByDocumentNu(documentNu).orElse(null);
		
		if(company != null) {
			client = clientDao.findByCompany(company).orElse(null);
		} else {
			Person person = personDao.findByDocumentNu(documentNu).orElse(null);
			
			if(person != null) {
				client = clientDao.findByPerson(person).orElse(null);
			}
		}
		
		return client;
	}
	
	public ApiResponse findByName(String name) {
		
		List<ClientDto> clients = new ArrayList<>();
		
		List<Company> companies = companyDao.searchByName(name);
		
		if(companies != null) {
			for(Company company : companies) {
				Client c = clientDao.findByCompany(company).orElse(null);
				if(c != null) {
					ClientDto client = clientConverter.fromEntity(c);
					clients.add(client);
				}
			}
		} 
		
		List<Person> persons = personDao.searchByName(name);
		
		if(persons != null) {
			for(Person person : persons) {
				Client c = clientDao.findByPerson(person).orElse(null);
				if(c != null) {
					ClientDto client = clientConverter.fromEntity(c);
					clients.add(client);
				}
			}
		}
		
		return ApiResponse.of(HttpStatus.OK.toString(), null, clients, clients.size());
	}
	
	public Client findById(Long id) {
		return clientDao.findById(id).orElse(null);
	}
	
	public Client save(String request) {
		Client client;

		JsonNode root;
		Integer typeId = null;
		Long personId = null;
		Integer companyId = null;
		Integer documentTypeId = null;
		String documentNu = null;
		String name = null;
		String lastname = null;
		String gender = null;
		String email = null;
		String legalName = null;
		String businessName = null;

		try {
			root = new ObjectMapper().readTree(request);

			typeId = root.path("typeId").asInt();
			personId = root.path("personId").asLong();
			companyId = root.path("companyId").asInt();
			documentTypeId = root.path("documentTypeId").asInt();
			documentNu = root.path("documentNu").asText();
			name = root.path("name").asText();
			lastname = root.path("lastname").asText();
			gender = root.path("gender").asText();
			email = root.path("email").asText();
			legalName = root.path("legalName").asText();
			businessName = root.path("businessName").asText();

		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros inválidos");
		}
		
		client = new Client();
		PersonType type = typeDao.findById(typeId).orElse(null);
		client.setType(type);
		
		if(type.getTypeId() == 1) { // Persona Jurídica
			client.setPerson(null);
			Company company = companyDao.findById(companyId).orElse(null);
			
			if(null == company) {
				company = new Company();

				company.setLegalName(legalName);
				company.setBusinessName(businessName);
				company.setDocumentNu(documentNu);

				company = companyDao.save(company);
			}
			
			client.setCompany(company);
			
		} else if(type.getTypeId() == 2) { // Persona Natural
			client.setCompany(null);
			Person person = personDao.findById(personId).orElse(null);
			
			if(null == person) {
				DocumentType docType = docTypeDao.findById(documentTypeId).orElse(null);
				
				person = new Person();
				
				person.setDocumentType(docType);
				person.setDocumentNu(documentNu);
				person.setName(name);
				person.setLastname(lastname);
				person.setGender(gender);
				person.setEmail(email);
				
				person = personDao.save(person);
			}
			
			client.setPerson(person);
			
		} else { // Tipo de Persona inválido
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de persona desconocido");
		}

		client = clientDao.save(client);

		return client;
	}

	public Client update(String request) {
		Client client;

		JsonNode root;
		Long clientId = null;
		Integer typeId = null;
		Long personId = null;
		Integer companyId = null;
		Integer documentTypeId = null;
		String documentNu = null;
		String name = null;
		String lastname = null;
		String gender = null;
		String email = null;
		String legalName = null;
		String businessName = null;

		try {
			root = new ObjectMapper().readTree(request);

			clientId = root.path("id").asLong();
			typeId = root.path("typeId").asInt();
			personId = root.path("personId").asLong();
			companyId = root.path("companyId").asInt();
			documentTypeId = root.path("documentTypeId").asInt();
			documentNu = root.path("documentNu").asText();
			name = root.path("name").asText();
			lastname = root.path("lastname").asText();
			gender = root.path("gender").asText();
			email = root.path("email").asText();
			legalName = root.path("legalName").asText();
			businessName = root.path("businessName").asText();

		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros inválidos");
		}
		
		client = new Client();
		client = clientDao.findById(clientId).orElse(null);
		
		if(client == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente desconocido");
		}
		
		PersonType type = typeDao.findById(typeId).orElse(null);
		client.setType(type);
		
		if(typeId == 1) { // Persona Jurídica
			client.setPerson(null);
			Company company = companyDao.findById(companyId).orElse(null);
			
			if(null == company) {
				company = new Company();

				company.setLegalName(legalName);
				company.setBusinessName(businessName);
				company.setDocumentNu(documentNu);

				company = companyDao.save(company);
			} else {
				company = new Company();

				company.setCompanyId(companyId);
				company.setLegalName(legalName);
				company.setBusinessName(businessName);
				company.setDocumentNu(documentNu);

				companyDao.updateCompany(company.getLegalName(), company.getBusinessName(), company.getDocumentNu(), company.getCompanyId());
				company = companyDao.findById(company.getCompanyId()).orElse(null);
			}
			
			client.setCompany(company);
			
		} else if(typeId == 2) { // Persona Natural
			client.setCompany(null);
			Person person = personDao.findById(personId).orElse(null);
			
			if(null == person) {
				DocumentType docType = docTypeDao.findById(documentTypeId).orElse(null);
				
				person = new Person();

				person.setDocumentType(docType);
				person.setDocumentNu(documentNu);
				person.setName(name);
				person.setLastname(lastname);
				person.setGender(gender);
				person.setEmail(email);
				
				person = personDao.save(person);
				
			} else {
				DocumentType docType = docTypeDao.findById(documentTypeId).orElse(null);
				
				person = new Person();
				
				person.setPersonId(personId);
				person.setDocumentType(docType);
				person.setDocumentNu(documentNu);
				person.setName(name);
				person.setLastname(lastname);
				person.setGender(gender);
				person.setEmail(email);
				
				personDao.updatePerson(person.getDocumentType(), person.getDocumentNu(), person.getName(), person.getLastname(),
						person.getGender(), person.getEmail(), person.getPersonId());
				
				person = personDao.findById(person.getPersonId()).orElse(null);
			}
			
			client.setPerson(person);
			
		} else { // Tipo de Persona inválido
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de persona desconocido");
		}

		client = clientDao.save(client);

		return client;
	}
}
