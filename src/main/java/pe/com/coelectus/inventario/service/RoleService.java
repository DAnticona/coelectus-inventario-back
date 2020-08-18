package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.coelectus.inventario.dao.RoleDao;
import pe.com.coelectus.inventario.entity.Role;

@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	
	public Role findByName(String name) {
		return roleDao.findByName(name).stream().findFirst().orElse(null);
	}

}
