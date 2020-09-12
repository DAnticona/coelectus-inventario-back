package pe.com.coelectus.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.coelectus.inventario.entity.Measure;

public interface MeasureDao extends JpaRepository<Measure, Integer>{

}
