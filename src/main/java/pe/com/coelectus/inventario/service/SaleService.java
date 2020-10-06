package pe.com.coelectus.inventario.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.ClientDao;
import pe.com.coelectus.inventario.dao.SaleDao;
import pe.com.coelectus.inventario.dao.SaleDetailDao;
import pe.com.coelectus.inventario.dao.StockDao;
import pe.com.coelectus.inventario.dto.ApiResponse;
import pe.com.coelectus.inventario.dto.SaleDto;
import pe.com.coelectus.inventario.dto.converter.SaleConverter;
import pe.com.coelectus.inventario.entity.Client;
import pe.com.coelectus.inventario.entity.Sale;
import pe.com.coelectus.inventario.entity.SaleDetail;
import pe.com.coelectus.inventario.entity.Stock;
import pe.com.coelectus.inventario.entity.id.SaleDetailId;
import pe.com.coelectus.inventario.entity.id.StockId;

@Service
public class SaleService {

	@Autowired
	SaleDao saleDao;
	@Autowired
	SaleDetailDao saleDetailDao;
	@Autowired
	ClientDao clientDao;
	@Autowired
	StockDao stockDao;
	@Autowired
	SaleConverter saleConverter;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static final int PAGE_LIMIT = 10;

	public ApiResponse findAll(Integer pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_LIMIT);
		Page<Sale> salesPage = saleDao.findAll(pageable);
		List<SaleDto> sales = saleConverter.fromEntity(salesPage.getContent());
		return ApiResponse.of("Codigo", "Mensaje", sales, Math.toIntExact(salesPage.getTotalElements()));
	}
	
	public ApiResponse findByDates(String start, String end) {
		
		LocalDate startDate = LocalDate.parse(start, formatter);
		LocalDate endDate = LocalDate.parse(end, formatter);
		
		List<SaleDto> sales = saleConverter.fromEntity(saleDao.findByDates(startDate, endDate));
		return ApiResponse.of(HttpStatus.OK.toString(), null, sales, sales.size());
	}

	public Sale findById(Long id) {
		return saleDao.findById(id).orElse(null);
	}

	public Sale save(String request) {
		Sale sale;

		JsonNode root;
		Long clientId = null;
		LocalDate date = null;
		Double amount = null;
		List<SaleDetail> details = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			root = mapper.readTree(request);

			clientId = root.path("clientId").asLong();
			date = LocalDate.parse(root.path("date").asText(), formatter);
			amount = root.path("amount").asDouble();

			JsonNode detailNode = root.path("details");

			if (detailNode.isArray()) {
				details = new ArrayList<>();

				for (JsonNode node : detailNode) {
					SaleDetail detail = this.extractDetail(node);
					details.add(detail);
				}
			}

		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros inválidos");
		}

		Client client = clientDao.findById(clientId).orElse(null);

		sale = new Sale();

		sale.setClient(client);
		sale.setDate(date);
		sale.setAmount(amount);
		sale.setActiveFg("S");

		sale = saleDao.save(sale);

		sale.setDetails(new ArrayList<>());
		for (int i = 0; i < details.size(); i++) {
			details.get(i).setSaleDetailId(new SaleDetailId(sale.getSaleId(), i + 1));
			details.get(i).setSale(sale);
			SaleDetail detail = saleDetailDao.save(details.get(i));
			
			if(detail.getServiceFg().equals("N")) {
				this.updateStock(detail.getStock().getStockId().getProductId(), detail.getStock().getStockId().getStockId(),
						detail.getQuantity());	
			}
			
			sale.getDetails().add(detail);
		}

		return sale;

	}

	public Sale annull(String request) {
		Sale sale;

		JsonNode root;
		Long saleId = null;
		String activeFg = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			root = mapper.readTree(request);

			saleId = root.path("saleId").asLong();
			activeFg = root.path("activeFg").asText();

		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetros inválidos");
		}

		sale = saleDao.findById(saleId).orElse(null);

		if (sale == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Venta desconocida");
		}
		
		if("N".equals(activeFg)) {
			sale.setActiveFg(activeFg);

			sale = saleDao.save(sale);

			for (int i = 0; i < sale.getDetails().size(); i++) {
				if(sale.getDetails().get(i).getServiceFg().equals("N")) {
					this.updateStock(sale.getDetails().get(i).getStock().getStockId().getProductId(),
							sale.getDetails().get(i).getStock().getStockId().getStockId(),
							sale.getDetails().get(i).getQuantity() * -1D);	
				}
			}
		}

		return sale;

	}

	private SaleDetail extractDetail(JsonNode detailNode) {

		SaleDetail detail;

		Long productId = null;
		Long stockId = null;
		Double quantity = null;
		Double unitPrice = null;
		Double totalPrice = null;
		String serviceFg = null;
		String serviceName = null;

		productId = detailNode.path("productId").asLong();
		stockId = detailNode.path("stockId").asLong();
		quantity = detailNode.path("quantity").asDouble();
		unitPrice = detailNode.path("unitPrice").asDouble();
		totalPrice = detailNode.path("totalPrice").asDouble();
		serviceFg = detailNode.path("serviceFg").asText();
		serviceName = detailNode.path("serviceName").asText();

		detail = new SaleDetail();
		
		if("N".equals(serviceFg)) {
			Stock stock = stockDao.findById(new StockId(stockId, productId)).orElse(null);

			if (stock == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto o stock desconocido");
			}

			detail.setServiceFg(serviceFg);
			detail.setStock(stock);
			detail.setQuantity(quantity);
			detail.setUnitPrice(unitPrice);
			detail.setTotalPrice(totalPrice);
			
		} else if("S".equals(serviceFg)) {
			detail.setServiceFg(serviceFg);
			detail.setServiceName(serviceName);
			detail.setQuantity(quantity);
			detail.setUnitPrice(unitPrice);
			detail.setTotalPrice(totalPrice);
		}


		return detail;

	}

	private void updateStock(Long productId, Long stockId, Double quantity) {

		Stock stock = stockDao.findById(new StockId(stockId, productId)).orElse(null);

		stock.setQuantity(stock.getQuantity() - quantity);

		stockDao.save(stock);
	}
}
