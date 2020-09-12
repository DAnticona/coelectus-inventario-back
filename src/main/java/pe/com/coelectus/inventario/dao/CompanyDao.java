package pe.com.coelectus.inventario.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pe.com.coelectus.inventario.entity.Company;

public interface CompanyDao extends JpaRepository<Company, Integer> {
	
	public Optional<Company> findByDocumentNu(String documentNu);

	@Query("SELECT c FROM Company c WHERE c.legalName like %:term% or c.businessName like %:term%")
	public List<Company> searchByName(String term);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Company c SET c.legalName = :legalName, c.businessName = :businessName, "
			+ "c.documentNu = :documentNu WHERE c.companyId = :id")
	public void updateCompany(String legalName, String businessName, String documentNu, Integer id);

}
