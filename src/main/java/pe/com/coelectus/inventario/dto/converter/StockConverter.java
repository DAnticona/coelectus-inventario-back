package pe.com.coelectus.inventario.dto.converter;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dto.StockDto;
import pe.com.coelectus.inventario.entity.Stock;
import pe.com.coelectus.inventario.entity.id.StockId;

@Component
public class StockConverter extends AbstractConverter<Stock, StockDto> {

	@Autowired
	ProductConverter productConverter;
	@Autowired
	MeasureConverter measureConverter;

	@Override
	public Stock fromDto(StockDto dto) {
		Stock stock = new Stock();
		stock.setQuantity(dto.getQuantity());
		stock.setUnitCost(dto.getUnitCost());
		stock.setUnitPrice(dto.getUnitPrice());

		if (dto.getDate() != null) {
			stock.setDate(Instant.ofEpochMilli(dto.getDate()).atZone(ZoneId.systemDefault()).toLocalDate());
		}

		if (dto.getStockId() != null && dto.getProductId() != null) {
			stock.setStockId(new StockId(dto.getStockId(), dto.getProductId()));
		}

		return stock;
	}

	@Override
	public StockDto fromEntity(Stock entity) {
		StockDto stock = new StockDto();
		stock.setQuantity(entity.getQuantity());
		stock.setUnitCost(entity.getUnitCost());
		stock.setUnitPrice(entity.getUnitPrice());

		if (entity.getDate() != null) {
			stock.setDate(Date.from(entity.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
		}

		if (entity.getStockId() != null) {
			stock.setStockId(entity.getStockId().getStockId());
			stock.setProductId(entity.getStockId().getProductId());
		}

		return stock;
	}

}
