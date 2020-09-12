package pe.com.coelectus.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.PersonType;

public interface PersonTypeDao extends JpaRepository<PersonType, Integer> {

}
