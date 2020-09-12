package pe.com.coelectus.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.Sale;
import pe.com.coelectus.inventario.entity.SaleDetail;
import pe.com.coelectus.inventario.entity.id.SaleDetailId;

public interface SaleDetailDao extends JpaRepository<SaleDetail, SaleDetailId> {
	
	public List<SaleDetail> findBySale(Sale sale);
	
	@Query(value = "SELECT ifnull(max(item), 0) FROM saleDetail WHERE sale_id = :saleId", nativeQuery = true)
	public Long getMaxItem(Long saleId);

}
