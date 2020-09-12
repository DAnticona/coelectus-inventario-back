package pe.com.coelectus.inventario.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.PersonDto;
import pe.com.coelectus.inventario.entity.Person;

@Component
public class PersonConverter extends AbstractConverter<Person, PersonDto> {

	@Autowired
	DocumentTypeConverter docTypeConverter;
	
	@Override
	public Person fromDto(PersonDto dto) {
		Person person = new Person();
		person.setPersonId(dto.getId());
		person.setDocumentNu(dto.getDocumentNu());
		person.setName(dto.getName());
		person.setLastname(dto.getLastname());
		person.setEmail(dto.getEmail());
		person.setGender(dto.getGender());

		if(dto.getDocType() != null) {
			person.setDocumentType(docTypeConverter.fromDto(dto.getDocType()));
		}

		return person;
	}

	@Override
	public PersonDto fromEntity(Person entity) {

		PersonDto person = new PersonDto();
		person.setId(entity.getPersonId());
		person.setDocumentNu(entity.getDocumentNu());
		person.setName(entity.getName());
		person.setLastname(entity.getLastname());
		person.setEmail(entity.getEmail());
		person.setGender(entity.getGender());
		
		if(entity.getDocumentType() != null) {
			person.setDocType(docTypeConverter.fromEntity(entity.getDocumentType()));
		}

		return person;
	}

}
