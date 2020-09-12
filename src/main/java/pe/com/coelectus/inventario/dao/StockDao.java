package pe.com.coelectus.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.Product;
import pe.com.coelectus.inventario.entity.Stock;
import pe.com.coelectus.inventario.entity.id.StockId;

public interface StockDao extends JpaRepository<Stock, StockId> {

	public List<Stock> findByProduct(Product product);

	@Query(value = "SELECT ifnull(max(stock_id), 0) FROM stock where product_id = :productId", nativeQuery = true)
	public Long getMaxId(Long productId);

	@Query(value = "SELECT s FROM Stock s WHERE s.quantity > 0")
	public List<Stock> findStocksBiggerThanZero();

}
