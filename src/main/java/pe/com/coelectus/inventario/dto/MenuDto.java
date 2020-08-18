package pe.com.coelectus.inventario.dto;

import java.util.List;

public class MenuDto {
	
	private Integer menuId;
	private String name;
	private Integer orderNu;
	private List<String> submenus;
	
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
	public List<String> getSubmenus() {
		return submenus;
	}
	public void setSubmenus(List<String> submenus) {
		this.submenus = submenus;
	}
	
}
