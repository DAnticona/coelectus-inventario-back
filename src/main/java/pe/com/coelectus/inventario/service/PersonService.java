package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.DocumentTypeDao;
import pe.com.coelectus.inventario.dao.PersonDao;
import pe.com.coelectus.inventario.entity.DocumentType;
import pe.com.coelectus.inventario.entity.Person;

@Service
public class PersonService {

	@Autowired
	PersonDao personDao;
	@Autowired
	DocumentTypeDao docTypeDao;

	public List<Person> findAll() {
		return personDao.findAll();
	}

	public Person findById(Person person) {
		return personDao.findById(person.getPersonId()).orElse(null);
	}

	public Person findByDocumentNu(String documentNu) {
		return personDao.findByDocumentNu(documentNu).orElse(null);
	}

	public Person save(String request) {
		Person person;

		JsonNode root;
		Integer documentTypeId = null;
		String documentNu = null;
		String name = null;
		String lastname = null;
		String gender = null;
		String email = null;

		try {
			root = new ObjectMapper().readTree(request);

			documentTypeId = root.path("documentTypeId").asInt();
			documentNu = root.path("documentNu").asText();
			name = root.path("name").asText();
			lastname = root.path("lastname").asText();
			gender = root.path("gender").asText();
			email = root.path("email").asText();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DocumentType docType = docTypeDao.findById(documentTypeId).orElse(null);

		person = new Person();

		person.setDocumentType(docType);
		person.setDocumentNu(documentNu);
		person.setName(name);
		person.setLastname(lastname);
		person.setGender(gender);
		person.setEmail(email);

		person = personDao.save(person);

		return person;
	}

	public Person update(String request) {

		Person person;

		JsonNode root;
		Long id = null;
		Integer documentTypeId = null;
		String documentNu = null;
		String name = null;
		String lastname = null;
		String gender = null;
		String email = null;

		try {
			root = new ObjectMapper().readTree(request);
			id = root.path("id").asLong();
			documentTypeId = root.path("documentTypeId").asInt();
			documentNu = root.path("documentNu").asText();
			name = root.path("name").asText();
			lastname = root.path("lastname").asText();
			gender = root.path("gender").asText();
			email = root.path("email").asText();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DocumentType docType = docTypeDao.findById(documentTypeId).orElse(null);

		person = new Person();

		person.setPersonId(id);
		person.setDocumentType(docType);
		person.setDocumentNu(documentNu);
		person.setName(name);
		person.setLastname(lastname);
		person.setGender(gender);
		person.setEmail(email);

		personDao.updatePerson(person.getDocumentType(), person.getDocumentNu(), person.getName(), person.getLastname(),
				person.getGender(), person.getEmail(), person.getPersonId());

		return personDao.findById(person.getPersonId()).orElse(null);
	}
}
