package pe.com.coelectus.inventario.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.UserDto;
import pe.com.coelectus.inventario.entity.User;

@Component
public class UserConverter extends AbstractConverter<User, UserDto> {
	
	@Autowired
	RoleConverter roleConverter;
	@Autowired
	DocumentTypeConverter docTypeConverter;

	@Override
	public User fromDto(UserDto dto) {
		User user = new User();
		user.setPersonId(dto.getId());
		user.setDocumentNu(dto.getDocumentNu());
		user.setName(dto.getName());
		user.setLastname(dto.getLastname());
		user.setEmail(dto.getEmail());
		user.setGender(dto.getGender());
		user.setImage(dto.getImage());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setActiveFg(dto.getActiveFg());

		if (dto.getRole() != null) {
			user.setRole(roleConverter.fromDto(dto.getRole()));
		}
		
		if(dto.getDocType() != null) {
			user.setDocumentType(docTypeConverter.fromDto(dto.getDocType()));
		}

		return user;
	}

	@Override
	public UserDto fromEntity(User entity) {

		UserDto user = new UserDto();
		user.setId(entity.getPersonId());
		user.setDocumentNu(entity.getDocumentNu());
		user.setName(entity.getName());
		user.setLastname(entity.getLastname());
		user.setEmail(entity.getEmail());
		user.setGender(entity.getGender());
		user.setImage(entity.getImage());
		user.setUsername(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setActiveFg(entity.getActiveFg());

		if (entity.getRole() != null) {
			user.setRole(roleConverter.fromEntity(entity.getRole()));
		}
		
		if(entity.getDocumentType() != null) {
			user.setDocType(docTypeConverter.fromEntity(entity.getDocumentType()));
		}

		return user;
	}

}
