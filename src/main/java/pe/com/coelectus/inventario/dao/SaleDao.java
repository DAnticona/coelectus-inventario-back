package pe.com.coelectus.inventario.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.Sale;

public interface SaleDao extends JpaRepository<Sale, Long> {
	
	@Override
	Page<Sale> findAll(Pageable pageable);
	
	@Query("SELECT s FROM Sale s WHERE s.date >= :startDate and s.date <= :endDate")
	public List<Sale> findByDates(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "SELECT ifnull(max(sale_id), 0) FROM sale", nativeQuery = true)
	public Long getMaxSaleId();

}
