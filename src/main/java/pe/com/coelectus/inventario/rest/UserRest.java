package pe.com.coelectus.inventario.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.coelectus.inventario.dto.UserDto;
import pe.com.coelectus.inventario.dto.converter.UserConverter;
import pe.com.coelectus.inventario.entity.User;
import pe.com.coelectus.inventario.service.PersonService;
import pe.com.coelectus.inventario.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRest {
	
	@Autowired
	UserService userService;
	@Autowired
	PersonService personService;
	@Autowired
	UserConverter userConverter;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<UserDto> users = userConverter.fromEntity(userService.findAll());
		
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findUserById(@PathVariable Long id) {
		User user = new User();
		user.setPersonId(id);
		
		UserDto userDto = userConverter.fromEntity(userService.findById(user));
		
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping("document/{nudoc}")
	public ResponseEntity<?> findUserById(@PathVariable String nudoc) {
		User user = new User();
		user.setDocumentNu(nudoc);
		
		UserDto userDto = userConverter.fromEntity(userService.findByDocumentNu(nudoc));
		
		return ResponseEntity.ok(userDto);
	}
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody String request) {
		
		UserDto user = userConverter.fromEntity(userService.saveUser(request));
		
		return ResponseEntity.ok(user);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody String request) {
		
		UserDto user = userConverter.fromEntity(userService.updateUser(request));
		
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("password")
	public ResponseEntity<?> updatePassword(@RequestBody String request) {
		
		UserDto user = userConverter.fromEntity(userService.updatePassword(request));
		
		return ResponseEntity.ok(user);
	}
	
	/**
	 * Método privado que lanza una exepción y la registra en el log ERROR en caso
	 * de encontrar un error
	 * 
	 * @param httpStatus Tipo de exception a lanzar.
	 * @param message    Mensaje a mostrar al Frontend.
	 */
//	private void throwException(HttpStatus httpStatus, String message) {
//		log.error(message);
//		throw new ResponseStatusException(httpStatus, message);
//	}
}
