package pe.com.coelectus.inventario.entity.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubmenuId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "menu_id")
	private Integer menuId;

	@Column(name = "submenu_id")
	private Integer submenuId;

	public SubmenuId() {
	}

	public SubmenuId(Integer idMenu, Integer idSubmenu) {
		this.menuId = idMenu;
		this.submenuId = idSubmenu;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((submenuId == null) ? 0 : submenuId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubmenuId other = (SubmenuId) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (submenuId == null) {
			if (other.submenuId != null)
				return false;
		} else if (!submenuId.equals(other.submenuId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubmenuId [idMenu=" + menuId + ", idSubmenu=" + submenuId + "]";
	}

}
