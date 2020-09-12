package pe.com.coelectus.inventario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long personId;
	
	@ManyToOne
	@JoinColumn(name = "doctype_id", referencedColumnName = "doctype_id")
	private DocumentType documentType;
	
	@Column(name="document_nu")
	private String documentNu;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name = "last_name", nullable = false)
	private String lastname;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name = "gender")
	private String gender;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNu() {
		return documentNu;
	}

	public void setDocumentNu(String documentNu) {
		this.documentNu = documentNu;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", documentType=" + documentType + ", documentNu=" + documentNu
				+ ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", gender=" + gender + "]";
	}
	
}
