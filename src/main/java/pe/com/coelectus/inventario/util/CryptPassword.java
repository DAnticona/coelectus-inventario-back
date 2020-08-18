package pe.com.coelectus.inventario.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptPassword {

	public static void main(String[] args) {
		String password = "system";

		System.out.println("password: " + password);
		System.out.println("password encriptado: " + encriptarPassword(password));

	}

	public static String encriptarPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}
