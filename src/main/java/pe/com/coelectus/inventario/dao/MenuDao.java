package pe.com.coelectus.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.Menu;

public interface MenuDao extends JpaRepository<Menu, Integer>{

}
