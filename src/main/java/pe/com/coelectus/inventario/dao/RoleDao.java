package pe.com.coelectus.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

	List<Role> findByName(String name);
	
}
