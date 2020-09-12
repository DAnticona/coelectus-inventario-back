package pe.com.coelectus.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.DocumentType;

public interface DocumentTypeDao extends JpaRepository<DocumentType, Integer> {

}
