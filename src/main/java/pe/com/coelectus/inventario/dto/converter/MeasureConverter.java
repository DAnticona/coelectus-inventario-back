package pe.com.coelectus.inventario.dto.converter;

import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.MeasureDto;
import pe.com.coelectus.inventario.entity.Measure;

@Component
public class MeasureConverter extends AbstractConverter<Measure, MeasureDto>{

	@Override
	public Measure fromDto(MeasureDto dto) {
		Measure measure = new Measure();
		measure.setMeasureId(dto.getMeasureId());
		measure.setName(dto.getName());
		measure.setSign(dto.getSign());
		
		return measure;
	}

	@Override
	public MeasureDto fromEntity(Measure entity) {
		MeasureDto measure = new MeasureDto();
		measure.setMeasureId(entity.getMeasureId());
		measure.setName(entity.getName());
		measure.setSign(entity.getSign());
		
		return measure;
	}

}
