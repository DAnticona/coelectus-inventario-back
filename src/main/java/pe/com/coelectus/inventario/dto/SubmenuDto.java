package pe.com.coelectus.inventario.dto;

public class SubmenuDto {

	private Integer menuId;
	private Integer submenuId;
	private String name;
	private Integer orderNu;
	private String path;
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getSubmenuId() {
		return submenuId;
	}
	public void setSubmenuId(Integer submenuId) {
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
}
