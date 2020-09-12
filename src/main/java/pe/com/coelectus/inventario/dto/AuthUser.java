package pe.com.coelectus.inventario.dto;

public class AuthUser {

	private UserDto user;

	private String token;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
