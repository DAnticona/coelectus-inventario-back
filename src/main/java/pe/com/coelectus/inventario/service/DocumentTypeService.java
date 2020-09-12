package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.coelectus.inventario.dao.DocumentTypeDao;
import pe.com.coelectus.inventario.entity.DocumentType;

@Service
public class DocumentTypeService {
	
	@Autowired
	DocumentTypeDao docTypeDao;
	
	public List<DocumentType> findAll() {
		return docTypeDao.findAll();
	}

}
