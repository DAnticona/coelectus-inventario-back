package pe.com.coelectus.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p WHERE p.name like %:name%")
	public List<Product> searchByName(String name);

}
