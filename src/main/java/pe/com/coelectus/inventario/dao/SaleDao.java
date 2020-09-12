package pe.com.coelectus.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.Sale;

public interface SaleDao extends JpaRepository<Sale, Long> {
	
	@Query(value = "SELECT ifnull(max(sale_id), 0) FROM sale", nativeQuery = true)
	public Long getMaxSaleId();

}
