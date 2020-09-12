package pe.com.coelectus.inventario.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.RoleDto;
import pe.com.coelectus.inventario.entity.Role;

@Component
public class RoleConverter extends AbstractConverter<Role, RoleDto>{

	@Autowired
	MenuConverter menuConverter;
	@Override
	public Role fromDto(RoleDto dto) {
		Role role = new Role();
		role.setRoleId(dto.getRoleId());
		role.setName(dto.getName());
		
		if(role.getMenus() != null) {
			role.setMenus(menuConverter.fromDto(dto.getMenus()));
		}

		return role;
	}

	@Override
	public RoleDto fromEntity(Role entity) {
		RoleDto role = new RoleDto();
		role.setRoleId(entity.getRoleId());
		role.setName(entity.getName());
		
		if(entity.getMenus() != null) {
			role.setMenus(menuConverter.fromEntity(entity.getMenus()));
		}
		return role;
	}

}
