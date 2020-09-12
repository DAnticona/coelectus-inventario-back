package pe.com.coelectus.inventario.dto.converter;

import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.SubmenuDto;
import pe.com.coelectus.inventario.entity.Submenu;
import pe.com.coelectus.inventario.entity.id.SubmenuId;

@Component
public class SubmenuConverter extends AbstractConverter<Submenu, SubmenuDto> {

	@Override
	public Submenu fromDto(SubmenuDto dto) {
		Submenu submenu = new Submenu();
		submenu.setSubmenuId(new SubmenuId(dto.getMenuId(), dto.getSubmenuId()));
		submenu.setName(dto.getName());
		submenu.setOrderNu(dto.getOrderNu());
		submenu.setPath(dto.getPath());

		return submenu;
	}

	@Override
	public SubmenuDto fromEntity(Submenu entity) {
		SubmenuDto submenu = new SubmenuDto();
		submenu.setMenuId(entity.getSubmenuId().getMenuId());
		submenu.setSubmenuId(entity.getSubmenuId().getSubmenuId());
		submenu.setName(entity.getName());
		submenu.setOrderNu(entity.getOrderNu());
		submenu.setPath(entity.getPath());

		return submenu;
	}

}
