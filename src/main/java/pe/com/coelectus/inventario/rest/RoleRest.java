package pe.com.coelectus.inventario.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.dto.RoleDto;
import pe.com.coelectus.inventario.dto.converter.RoleConverter;
import pe.com.coelectus.inventario.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleRest {
	
	@Autowired
	RoleService roleService;
	@Autowired
	RoleConverter roleConverter;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(roleService.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(roleService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> saveRole(@RequestBody String request) {
		
		RoleDto role = roleConverter.fromEntity(roleService.saveRole(request));
		
		return ResponseEntity.ok(role);
	}
	
	@PutMapping
	public ResponseEntity<?> updateRole(@RequestBody String request) {
		
		RoleDto role = roleConverter.fromEntity(roleService.updateRole(request));
		
		return ResponseEntity.ok(role);
	}

}
