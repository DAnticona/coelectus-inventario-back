package pe.com.coelectus.inventario.dto.converter;

import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.PersonTypeDto;
import pe.com.coelectus.inventario.entity.PersonType;

@Component
public class PersonTypeConverter extends AbstractConverter<PersonType, PersonTypeDto>{

	@Override
	public PersonType fromDto(PersonTypeDto dto) {
		PersonType type = new PersonType();
		type.setTypeId(dto.getTypeId());
		type.setName(dto.getName());
		type.setShortname(dto.getShortname());
		return type;
	}

	@Override
	public PersonTypeDto fromEntity(PersonType entity) {
		PersonTypeDto type = new PersonTypeDto();
		type.setTypeId(entity.getTypeId());
		type.setName(entity.getName());
		type.setShortname(entity.getShortname());
		return type;
	}

}
