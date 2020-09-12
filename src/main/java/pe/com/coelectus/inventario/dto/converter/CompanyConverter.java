package pe.com.coelectus.inventario.dto.converter;

import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.CompanyDto;
import pe.com.coelectus.inventario.entity.Company;

@Component
public class CompanyConverter extends AbstractConverter<Company, CompanyDto>{

	@Override
	public Company fromDto(CompanyDto dto) {
		Company company = new Company();
		company.setCompanyId(dto.getCompanyId());
		company.setLegalName(dto.getLegalName());
		company.setBusinessName(dto.getBusinessName());
		company.setDocumentNu(dto.getDocumentNu());
		
		return company;
	}

	@Override
	public CompanyDto fromEntity(Company entity) {
		CompanyDto company = new CompanyDto();
		company.setCompanyId(entity.getCompanyId());
		company.setLegalName(entity.getLegalName());
		company.setBusinessName(entity.getBusinessName());
		company.setDocumentNu(entity.getDocumentNu());
		
		return company;
	}

}
