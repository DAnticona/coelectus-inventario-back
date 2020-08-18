package pe.com.coelectus.inventario.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.UserDto;
import pe.com.coelectus.inventario.entity.User;
import pe.com.coelectus.inventario.service.RoleService;

@Component
public class UserConverter extends AbstractConverter<User, UserDto> {

	@Autowired
	RoleService roleService;

	@Override
	public User fromDto(UserDto dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());

		if (dto.getRole() != null) {
			user.setRole(roleService.findByName(dto.getRole()));
		}

		return user;
	}

	@Override
	public UserDto fromEntity(User entity) {

		UserDto user = new UserDto();
		user.setUsername(entity.getUsername());
		user.setPassword(entity.getPassword());

		if (entity.getRole() != null) {
			user.setRole(entity.getRole().getName());
		}

		return user;
	}

}
