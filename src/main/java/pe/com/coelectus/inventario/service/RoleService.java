package pe.com.coelectus.inventario.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import pe.com.coelectus.inventario.dao.MenuDao;
import pe.com.coelectus.inventario.dao.RoleDao;
import pe.com.coelectus.inventario.entity.Menu;
import pe.com.coelectus.inventario.entity.Role;

@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;
	@Autowired
	MenuDao menuDao;
	
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	
	public Role findByName(String name) {
		return roleDao.findByName(name).stream().findFirst().orElse(null);
	}
	
	public Role findById(Integer id) {
		return roleDao.findById(id).orElse(null);
	}
	
	public Role saveRole(String request) {
		Role role;

		JsonNode root;
		String name = null;
		List<Menu> menus = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			root = new ObjectMapper().readTree(request);

			name = root.path("name").asText();
			JsonNode nodeMenu = root.path("menusId");
			
			ObjectReader reader = mapper.readerFor(new TypeReference<List<Integer>>() {});
			List<Integer> menusId = null;
			if (null != reader) {
				menusId = reader.readValue(nodeMenu);
			}

			menus = new ArrayList<>();

			for (Integer menuId : menusId) {
				Menu menu = menuDao.findById(menuId).orElse(null);
				menus.add(menu);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		role = new Role();

		role.setName(name);
		role.setMenus(menus);

		role = roleDao.save(role);

		return role;
	}
	
	public Role updateRole(String request) {
		Role role;

		JsonNode root;
		Integer id = null;
		String name = null;
		List<Menu> menus = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			root = new ObjectMapper().readTree(request);

			id = root.path("id").asInt();
			name = root.path("name").asText();
			JsonNode nodeMenu = root.path("menusId");
			
			ObjectReader reader = mapper.readerFor(new TypeReference<List<Integer>>() {});
			List<Integer> menusId = null;
			if (null != reader) {
				menusId = reader.readValue(nodeMenu);
			}

			menus = new ArrayList<>();

			for (Integer menuId : menusId) {
				Menu menu = menuDao.findById(menuId).orElse(null);
				menus.add(menu);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		role = new Role();

		role.setRoleId(id);
		role.setName(name);
		role.setMenus(menus);

		role = roleDao.save(role);

		return role;
	}

}
