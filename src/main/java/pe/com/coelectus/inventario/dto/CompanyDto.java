package pe.com.coelectus.inventario.dto;

public class CompanyDto {

	private Integer companyId;
	private String legalName;
	private String businessName;
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
		return "CompanyDto [companyId=" + companyId + ", legalName=" + legalName + ", businessName=" + businessName
				+ ", documentNu=" + documentNu + "]";
	}
}
