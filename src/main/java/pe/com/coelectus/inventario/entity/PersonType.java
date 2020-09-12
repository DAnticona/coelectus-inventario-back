package pe.com.coelectus.inventario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_type")
public class PersonType {
	
	@Id
	@Column(name ="type_id")
	private Integer typeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "shortname")
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
		return "PersonType [typeId=" + typeId + ", name=" + name + ", shortname=" + shortname + "]";
	}

}
