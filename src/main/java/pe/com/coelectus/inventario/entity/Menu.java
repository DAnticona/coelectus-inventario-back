package pe.com.coelectus.inventario.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "order_nu")
	private Integer orderNu;
	
	@OneToMany(mappedBy = "menu")
	@JsonManagedReference
	private List<Submenu> submenus;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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

	public List<Submenu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Submenu> submenus) {
		this.submenus = submenus;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", name=" + name + ", orderNu=" + orderNu + ", submenus=" + submenus + "]";
	}
	
}
