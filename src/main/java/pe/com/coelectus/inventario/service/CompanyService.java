package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.CompanyDao;
import pe.com.coelectus.inventario.entity.Company;

@Service
public class CompanyService {
	
	@Autowired
	CompanyDao companyDao;
	
	public Company findByDocumentNu(String documentNu) {
		return companyDao.findByDocumentNu(documentNu).orElse(null);
	}	
	
	public List<Company> findAll() {
		return companyDao.findAll();
	}
	
	public List<Company> searchByName(String term) {
		return companyDao.searchByName(term);
	}

	public Company save(String request) {
		Company company;

		JsonNode root;
		String legalName = null;
		String businessName = null;
		String documentNu = null;

		try {
			root = new ObjectMapper().readTree(request);

			legalName = root.path("legalName").asText();
			businessName = root.path("businessName").asText();
			documentNu = root.path("documentNu").asText();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		company = new Company();

		company.setLegalName(legalName);
		company.setBusinessName(businessName);
		company.setDocumentNu(documentNu);

		company = companyDao.save(company);

		return company;
	}

	public Company update(String request) {
		Company company;

		JsonNode root;
		Integer companyId = null;
		String legalName = null;
		String businessName = null;
		String documentNu = null;

		try {
			root = new ObjectMapper().readTree(request);

			companyId = root.path("id").asInt();
			legalName = root.path("legalName").asText();
			businessName = root.path("businessName").asText();
			documentNu = root.path("documentNu").asText();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		company = new Company();
		company.setCompanyId(companyId);
		company.setLegalName(legalName);
		company.setBusinessName(businessName);
		company.setDocumentNu(documentNu);

		companyDao.updateCompany(company.getLegalName(), company.getBusinessName(), company.getDocumentNu(), company.getCompanyId());

		return companyDao.findById(company.getCompanyId()).orElse(null);
	}

}
