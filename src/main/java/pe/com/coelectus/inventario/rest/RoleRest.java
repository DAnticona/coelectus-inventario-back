package pe.com.coelectus.inventario.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleRest {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(roleService.findAll());
	}

}
