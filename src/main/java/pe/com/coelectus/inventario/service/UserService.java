package pe.com.coelectus.inventario.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
	
	@Value("${user.image.path}")
	private String userImagePath;
	@Value("${user.image.url}")
	private String userImageUrl;

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
	
	public User saveImage(Long userId, MultipartFile multipartFile) {
		
		User user = userDao.findById(userId).orElse(null);
		
		if(user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario desconocido");
		}
		
		String extension = this.getExtensionByStringHandling(multipartFile.getOriginalFilename()).orElse(null);
		
		if(null == extension) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato no válido");
		}
		
		String filename = UUID.randomUUID() + "." + extension;
		
		File file = new File(this.userImagePath + filename);

		try {

			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();
			
			System.out.println(user.getImageFilename());
			
			if(null != user.getImageFilename()) {
				String oldFilename = this.userImagePath + user.getImageFilename();
				
				File oldFile = new File(oldFilename);
				oldFile.delete();
			}
			
			
			
			
			String image = this.userImageUrl + filename;
			
			user.setImage(image);
			user.setImageFilename(filename);
			user = userDao.save(user);
			
			return user;

		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al procesar el archivo", e);
		}
	}
	
	private Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
}
