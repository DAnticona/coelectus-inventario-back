package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.coelectus.inventario.dao.SubmenuDao;
import pe.com.coelectus.inventario.entity.Submenu;

@Service
public class SubmenuService {
	
	@Autowired
	SubmenuDao submenuDao;
	
	public List<Submenu> findAll() {
		return submenuDao.findAll();
	}
}
