package pe.com.coelectus.inventario.dto;

public class PersonDto {

	private Long id;
	private DocumentTypeDto docType;
	private String documentNu;
	private String name;
	private String lastname;
	private String email;
	private String gender;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DocumentTypeDto getDocType() {
		return docType;
	}
	public void setDocType(DocumentTypeDto docType) {
		this.docType = docType;
	}
	public String getDocumentNu() {
		return documentNu;
	}
	public void setDocumentNu(String documentNu) {
		this.documentNu = documentNu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "PersonDto [id=" + id + ", docType=" + docType + ", documentNu=" + documentNu + ", name=" + name
				+ ", lastname=" + lastname + ", email=" + email + ", gender=" + gender + "]";
	}
	
}
