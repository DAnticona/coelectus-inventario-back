package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.coelectus.inventario.dao.PersonTypeDao;
import pe.com.coelectus.inventario.entity.PersonType;

@Service
public class PersonTypeService {
	
	@Autowired
	PersonTypeDao typeDao;
	
	public List<PersonType> findAll() {
		return typeDao.findAll();
	}

}
