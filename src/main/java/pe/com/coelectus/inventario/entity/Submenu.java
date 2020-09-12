package pe.com.coelectus.inventario.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import pe.com.coelectus.inventario.entity.id.SubmenuId;

@Entity
@Table(name = "submenu")
public class Submenu {

	@EmbeddedId
	private SubmenuId submenuId;

	@Column(name = "name")
	private String name;

	@Column(name = "order_nu")
	private Integer orderNu;
	
	@Column
	private String path;
	
	@MapsId("idMenu")
	@ManyToOne
	@JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
	@JsonBackReference
	private Menu menu;

	public SubmenuId getSubmenuId() {
		return submenuId;
	}

	public void setSubmenuId(SubmenuId submenuId) {
		this.submenuId = submenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNu() {
		return orderNu;
	}

	public void setOrderNu(Integer orderNu) {
		this.orderNu = orderNu;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "Submenu [submenuId=" + submenuId + ", name=" + name + ", orderNu=" + orderNu + ", menu=" + menu + "]";
	}

}
