package pe.com.coelectus.inventario.dto;

public class UserDto {
	
	private Long id;
	private DocumentTypeDto docType;
	private String documentNu;
	private String name;
	private String lastname;
	private String email;
	private String gender;
	private String activeFg;
	private String image;
	private String imageFilename;
	private String username;
	private String password;
	private RoleDto role;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getActiveFg() {
		return activeFg;
	}

	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
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
	
	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", docType=" + docType + ", documentNu=" + documentNu + ", name=" + name
				+ ", lastname=" + lastname + ", email=" + email + ", gender=" + gender + ", activeFg=" + activeFg
				+ ", image=" + image + ", imageFilename=" + imageFilename + ", username=" + username + ", password="
				+ password + ", role=" + role + "]";
	}

}
