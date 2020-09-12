package pe.com.coelectus.inventario.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.Client;
import pe.com.coelectus.inventario.entity.Company;
import pe.com.coelectus.inventario.entity.Person;

public interface ClientDao extends JpaRepository<Client, Long>{

	public Optional<Client> findByPerson(Person person);
	
	public Optional<Client> findByCompany(Company company);
}
