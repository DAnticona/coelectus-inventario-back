package pe.com.coelectus.inventario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DocumentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctype_id")
	private Integer doctypeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="shortname")
	private String shortname;

	public Integer getDoctypeId() {
		return doctypeId;
	}

	public void setDoctypeId(Integer doctypeId) {
		this.doctypeId = doctypeId;
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
		return "DocumentType [doctypeId=" + doctypeId + ", name=" + name + ", shortname=" + shortname + "]";
	}

}
