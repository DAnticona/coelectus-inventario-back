package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.coelectus.inventario.dao.MenuDao;
import pe.com.coelectus.inventario.entity.Menu;
import pe.com.coelectus.inventario.entity.Role;

@Service
public class MenuService {
	
	@Autowired
	MenuDao menuDao;
	
	public List<Menu> findAll() {
		return menuDao.findAll();
	}
	
	public List<Menu> findByRole(Role role) {
		return null;
	}
	
	public Menu findByID(Menu menu) {
		return menuDao.findById(menu.getMenuId()).orElse(null);
	}
	
	public Menu save(Menu menu) {
		return menuDao.save(menu);
	}
	
	public void delete(Menu menu) {
		menuDao.delete(menu);
	}

}
