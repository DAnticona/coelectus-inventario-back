package pe.com.coelectus.inventario.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;
import pe.com.coelectus.inventario.dto.AuthUser;
import pe.com.coelectus.inventario.entity.User;
import pe.com.coelectus.inventario.service.UserService;
import pe.com.coelectus.inventario.util.JwtToken;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginRest {

	@Autowired
	UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtToken jwtToken;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody User user) {
		this.autenticar(user.getUsername(), user.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

		final String token = jwtToken.generateToken(userDetails);

		user = new User(user.getUsername());
		user = userService.findByUsername(user);

		AuthUser authUser = new AuthUser(user.getPersonId(), user.getRole().getName(), user.getUsername(),
				user.getName() + " " + user.getLastName(), user.getEmail(), user.getGender(), token);

		return ResponseEntity.ok(authUser);

	}

	private void autenticar(String username, String password) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {

			log.info("USER_DISABLED");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "USER DISABLED", e);

		} catch (BadCredentialsException e) {

			log.info("INVALID_CREDENTIALS");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "INVALID CREDENTIALS", e);

		}
	}

}
