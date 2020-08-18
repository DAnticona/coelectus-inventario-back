package pe.com.coelectus.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	public User findByUsername(String username);
}
