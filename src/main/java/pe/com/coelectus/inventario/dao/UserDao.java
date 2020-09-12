package pe.com.coelectus.inventario.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.DocumentType;
import pe.com.coelectus.inventario.entity.Role;
import pe.com.coelectus.inventario.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	public User findByDocumentNu(String documentNu);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE User u SET u.documentType = :documentType, u.documentNu = :documentNu , "
			+ "u.name = :name, u.lastname = :lastname, u.gender = :gender, u.email = :email , "
			+ "u.activeFg = :activeFg, u.role = :role WHERE u.personId = :id")
	public void updateUser(DocumentType documentType, String documentNu, String name, String lastname, String gender,
			String email, String activeFg, Role role, Long id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE User u SET u.password = :password WHERE u.personId = :id")
	public void updatePassword(String password, Long id);
}
