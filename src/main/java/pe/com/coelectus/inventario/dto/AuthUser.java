package pe.com.coelectus.inventario.dto;

public class AuthUser {

	private Long id;

	private String role;

	private String username;

	private String name;

	private String email;

	private String gender;

	private String token;

	public AuthUser(Long id, String role, String username, String name, String email, String gender, String token) {
		this.id = id;
		this.role = role;
		this.username = username;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
