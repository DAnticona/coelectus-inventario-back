package pe.com.coelectus.inventario.dto.converter;

import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.MenuDto;
import pe.com.coelectus.inventario.entity.Menu;

@Component
public class MenuConverter extends AbstractConverter<Menu, MenuDto> {

	@Override
	public Menu fromDto(MenuDto dto) {
		Menu menu = new Menu();
		menu.setMenuId(dto.getMenuId());
		menu.setName(dto.getName());
		menu.setOrderNu(dto.getOrderNu());

		return menu;
	}

	@Override
	public MenuDto fromEntity(Menu entity) {
		MenuDto menu = new MenuDto();
		menu.setMenuId(entity.getMenuId());
		menu.setName(entity.getName());
		menu.setOrderNu(entity.getOrderNu());

		return menu;
	}

}
