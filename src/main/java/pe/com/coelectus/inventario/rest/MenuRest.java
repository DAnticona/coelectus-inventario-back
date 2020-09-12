package pe.com.coelectus.inventario.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.dto.MenuDto;
import pe.com.coelectus.inventario.dto.converter.MenuConverter;
import pe.com.coelectus.inventario.service.MenuService;

@RestController
@RequestMapping("/menus")
public class MenuRest {

	@Autowired
	MenuService menuService;
	@Autowired
	MenuConverter menuConverter;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<MenuDto> menus = menuConverter.fromEntity(menuService.findAll());
		return ResponseEntity.ok(menus);
	}
}
