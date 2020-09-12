package pe.com.coelectus.inventario.dto;

public class PersonTypeDto {

	private Integer typeId;
	private String name;
	private String shortname;
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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
		return "PersonTypeDto [typeId=" + typeId + ", name=" + name + ", shortname=" + shortname + "]";
	}
}
