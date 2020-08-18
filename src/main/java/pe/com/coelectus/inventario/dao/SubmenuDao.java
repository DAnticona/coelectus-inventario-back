package pe.com.coelectus.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.Submenu;
import pe.com.coelectus.inventario.entity.id.SubmenuId;

public interface SubmenuDao extends JpaRepository<Submenu, SubmenuId>{

}
