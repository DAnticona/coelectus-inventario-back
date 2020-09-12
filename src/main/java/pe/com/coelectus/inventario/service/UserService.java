package pe.com.coelectus.inventario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.DocumentTypeDao;
import pe.com.coelectus.inventario.dao.RoleDao;
import pe.com.coelectus.inventario.dao.UserDao;
import pe.com.coelectus.inventario.entity.DocumentType;
import pe.com.coelectus.inventario.entity.Role;
import pe.com.coelectus.inventario.entity.User;
import pe.com.coelectus.inventario.util.CryptPassword;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

	@Autowired
	UserDao userDao;
	@Autowired
	DocumentTypeDao docTypeDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	CryptPassword cryptPassword;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> roles = new ArrayList<>();

		roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findByUsername(User user) {
		User u = userDao.findByUsername(user.getUsername());
		return u;
	}

	public User findById(User user) {
		return userDao.findById(user.getPersonId()).orElse(null);
	}

	public User findByDocumentNu(String documentNu) {
		return userDao.findByDocumentNu(documentNu);
	}

	public User saveUser(String request) {
		User user;

		JsonNode root;
		Integer documentTypeId = null;
		String documentNu = null;
		String name = null;
		String lastname = null;
		String gender = null;
		String email = null;
		String username = null;
		String password = null;
		String activeFg = null;
		Integer roleId = null;

		try {
			root = new ObjectMapper().readTree(request);

			documentTypeId = root.path("documentTypeId").asInt();
			documentNu = root.path("documentNu").asText();
			name = root.path("name").asText();
			lastname = root.path("lastname").asText();
			gender = root.path("gender").asText();
			email = root.path("email").asText();
			username = root.path("username").asText();
			password = root.path("password").asText();
			activeFg = root.path("activeFg").asText();
			roleId = root.path("roleId").asInt();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DocumentType docType = docTypeDao.findById(documentTypeId).orElse(null);
		Role role = roleDao.findById(roleId).orElseThrow(null);

		user = new User();

		user.setDocumentType(docType);
		user.setDocumentNu(documentNu);
		user.setName(name);
		user.setLastname(lastname);
		user.setGender(gender);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(cryptPassword.encode(password));
		user.setActiveFg(activeFg);
		user.setRole(role);

		user = userDao.save(user);

		return user;
	}

	public User updateUser(String request) {

		User user;

		JsonNode root;
		Long id = null;
		Integer documentTypeId = null;
		String documentNu = null;
		String name = null;
		String lastname = null;
		String gender = null;
		String email = null;
		String activeFg = null;
		Integer roleId = null;

		try {
			root = new ObjectMapper().readTree(request);
			id = root.path("id").asLong();
			documentTypeId = root.path("documentTypeId").asInt();
			documentNu = root.path("documentNu").asText();
			name = root.path("name").asText();
			lastname = root.path("lastname").asText();
			gender = root.path("gender").asText();
			email = root.path("email").asText();
			activeFg = root.path("activeFg").asText();
			roleId = root.path("roleId").asInt();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DocumentType docType = docTypeDao.findById(documentTypeId).orElse(null);
		Role role = roleDao.findById(roleId).orElseThrow(null);

		user = new User();

		user.setPersonId(id);
		user.setDocumentType(docType);
		user.setDocumentNu(documentNu);
		user.setName(name);
		user.setLastname(lastname);
		user.setGender(gender);
		user.setEmail(email);
		user.setActiveFg(activeFg);
		user.setRole(role);

		userDao.updateUser(user.getDocumentType(), user.getDocumentNu(), user.getName(), user.getLastname(),
				user.getGender(), user.getEmail(), user.getActiveFg(), user.getRole(),
				user.getPersonId());
		
		return userDao.findById(user.getPersonId()).orElse(null);
	}

	public User updatePassword(String request) {
		
		User user;

		JsonNode root;
		Long id = null;
		String password = null;

		try {
			root = new ObjectMapper().readTree(request);
			id = root.path("id").asLong();
			password = root.path("password").asText();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user = new User();

		user.setPersonId(id);
		user.setPassword(cryptPassword.encode(password));

		userDao.updatePassword(user.getPassword(), user.getPersonId());
		
		return userDao.findById(user.getPersonId()).orElse(null);
		
	}
}
