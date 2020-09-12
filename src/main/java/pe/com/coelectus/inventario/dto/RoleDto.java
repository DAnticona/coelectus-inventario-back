package pe.com.coelectus.inventario.dto;

import java.util.List;

public class RoleDto {
	
	private Integer RoleId;
	private String name;
	private List<MenuDto> menus;
	
	public Integer getRoleId() {
		return RoleId;
	}
	public void setRoleId(Integer RoleId) {
		this.RoleId = RoleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MenuDto> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDto> menus) {
		this.menus = menus;
	}
	@Override
	public String toString() {
		return "RoleDto [Id=" + RoleId + ", name=" + name + "]";
	}

}
