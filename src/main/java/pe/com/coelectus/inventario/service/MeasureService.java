package pe.com.coelectus.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.MeasureDao;
import pe.com.coelectus.inventario.entity.Measure;

@Service
public class MeasureService {
	
	@Autowired
	MeasureDao measureDao;
	
	public List<Measure> findAll() {
		return measureDao.findAll();
	}
	
	public Measure findById(Integer id) {
		return measureDao.findById(id).orElse(null);
	}
	
	public Measure save(String request) {
		Measure measure;

		JsonNode root;
		String name = null;
		String sign = null;

		try {
			root = new ObjectMapper().readTree(request);

			name = root.path("name").asText();
			sign = root.path("sign").asText();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		measure = new Measure();

		measure.setName(name);
		measure.setSign(sign);

		measure = measureDao.save(measure);

		return measure;
	}
	
	public Measure update(String request) {
		Measure measure;

		JsonNode root;
		Integer measureId = null;
		String name = null;
		String sign = null;

		try {
			root = new ObjectMapper().readTree(request);

			measureId = root.path("id").asInt();
			name = root.path("name").asText();
			sign = root.path("sign").asText();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		measure = new Measure();

		measure.setMeasureId(measureId);
		measure.setName(name);
		measure.setSign(sign);

		measure = measureDao.save(measure);

		return measure;
	}

}
