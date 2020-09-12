package pe.com.coelectus.inventario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Integer companyId;
	
	@Column(name = "legal_name")
	private String legalName;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(name = "document_nu")
	private String documentNu;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getDocumentNu() {
		return documentNu;
	}

	public void setDocumentNu(String documentNu) {
		this.documentNu = documentNu;
	}

	@Override
	public String toString() {
		return "Company [compadyId=" + companyId + ", legalName=" + legalName + ", businessName=" + businessName
				+ ", documentNu=" + documentNu + "]";
	}
}
