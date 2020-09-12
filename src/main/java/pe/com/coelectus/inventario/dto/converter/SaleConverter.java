package pe.com.coelectus.inventario.dto.converter;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.SaleDto;
import pe.com.coelectus.inventario.entity.Sale;

@Component
public class SaleConverter extends AbstractConverter<Sale, SaleDto> {
	
	@Autowired
	SaleDetailConverter detailConverter;
	@Autowired
	ClientConverter clientConverter;

	@Override
	public Sale fromDto(SaleDto dto) {
		Sale sale = new Sale();
		sale.setSaleId(dto.getSaleId());
		sale.setAmount(dto.getAmount());
		sale.setActiveFg(dto.getActiveFg());
		
		if (dto.getDate() != null) {
			sale.setDate(Instant.ofEpochMilli(dto.getDate()).atZone(ZoneId.systemDefault()).toLocalDate());
		}
		
		if(dto.getClient() != null) {
			sale.setClient(clientConverter.fromDto(dto.getClient()));
		}
		
		if(dto.getDetails() != null) {
			sale.setDetails(detailConverter.fromDto(dto.getDetails()));
		}
		
		return sale;
	}

	@Override
	public SaleDto fromEntity(Sale entity) {
		SaleDto sale = new SaleDto();
		sale.setSaleId(entity.getSaleId());
		sale.setAmount(entity.getAmount());
		sale.setActiveFg(entity.getActiveFg());
		
		if (entity.getDate() != null) {
			sale.setDate(Date.from(entity.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
		}
		
		if(entity.getClient() != null) {
			sale.setClient(clientConverter.fromEntity(entity.getClient()));
		}
		
		if(entity.getDetails() != null) {
			sale.setDetails(detailConverter.fromEntity(entity.getDetails()));
		}
		
		return sale;
	}

}
