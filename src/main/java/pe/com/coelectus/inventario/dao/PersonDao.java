package pe.com.coelectus.inventario.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.DocumentType;
import pe.com.coelectus.inventario.entity.Person;

public interface PersonDao extends JpaRepository<Person, Long> {

	public Optional<Person> findByDocumentNu(String documentNu);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Person p SET p.documentType = :documentType, p.documentNu = :documentNu, "
			+ "p.name = :name, p.lastname = :lastname, p.gender = :gender, p.email = :email "
			+ "WHERE p.personId = :id")
	public void updatePerson(DocumentType documentType, String documentNu, String name, String lastname, String gender,
			String email, Long id);

}
