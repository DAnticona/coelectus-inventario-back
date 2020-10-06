package pe.com.coelectus.inventario.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.coelectus.inventario.dao.SaleDao;
import pe.com.coelectus.inventario.dto.SaleDetailDto;
import pe.com.coelectus.inventario.entity.SaleDetail;
import pe.com.coelectus.inventario.entity.id.SaleDetailId;

@Component
public class SaleDetailConverter extends AbstractConverter<SaleDetail, SaleDetailDto>{
	
	@Autowired
	StockConverter stockConverter;
	@Autowired
	SaleDao saleDao;

	@Override
	public SaleDetail fromDto(SaleDetailDto dto) {
		SaleDetail detail = new SaleDetail();
		detail.setQuantity(dto.getQuantity());
		detail.setUnitPrice(dto.getUnitPrice());
		detail.setTotalPrice(dto.getTotalPrice());
		detail.setServiceFg(dto.getServiceFg());
		detail.setServiceName(dto.getServiceName());
		
		if(dto.getSaleId() != null) {
			detail.setSale(saleDao.findById(dto.getSaleId()).orElse(null));
		}
		
		if(dto.getStock() != null) {
			detail.setStock(stockConverter.fromDto(dto.getStock()));
		}
		
		if(dto.getSaleId() != null && dto.getItem() != null) {
			detail.setSaleDetailId(new SaleDetailId(dto.getSaleId(), dto.getItem()));
		}
		
		return detail;
	}

	@Override
	public SaleDetailDto fromEntity(SaleDetail entity) {
		SaleDetailDto detail = new SaleDetailDto();
		detail.setQuantity(entity.getQuantity());
		detail.setUnitPrice(entity.getUnitPrice());
		detail.setTotalPrice(entity.getTotalPrice());
		detail.setServiceFg(entity.getServiceFg());
		detail.setServiceName(entity.getServiceName());
		
		if(entity.getStock() != null) {
			detail.setStock(stockConverter.fromEntity(entity.getStock()));
		}
		
		if(entity.getSaleDetailId() != null) {
			detail.setSaleId(entity.getSaleDetailId().getSaleId());
			detail.setItem(entity.getSaleDetailId().getItem());
		}
		
		return detail;
	}

}
