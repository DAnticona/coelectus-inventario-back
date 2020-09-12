package pe.com.coelectus.inventario.dto;

public class DocumentTypeDto {

	private Integer id;
	private String name;
	private String shortname;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	@Override
	public String toString() {
		return "DocumentTypeDto [id=" + id + ", name=" + name + ", shortname=" + shortname + "]";
	}
}
