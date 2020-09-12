package pe.com.coelectus.inventario.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.coelectus.inventario.dao.ClientDao;
import pe.com.coelectus.inventario.dao.SaleDao;
import pe.com.coelectus.inventario.dao.SaleDetailDao;
import pe.com.coelectus.inventario.dao.StockDao;
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

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public List<Sale> findAll() {
		return saleDao.findAll();
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
			this.updateStock(detail.getStock().getStockId().getProductId(), detail.getStock().getStockId().getStockId(),
					detail.getQuantity());
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
				this.updateStock(sale.getDetails().get(i).getStock().getStockId().getProductId(),
						sale.getDetails().get(i).getStock().getStockId().getStockId(),
						sale.getDetails().get(i).getQuantity() * -1D);
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

		productId = detailNode.path("productId").asLong();
		stockId = detailNode.path("stockId").asLong();
		quantity = detailNode.path("quantity").asDouble();
		unitPrice = detailNode.path("unitPrice").asDouble();
		totalPrice = detailNode.path("totalPrice").asDouble();

		detail = new SaleDetail();
		Stock stock = stockDao.findById(new StockId(stockId, productId)).orElse(null);

		if (stock == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto o stock desconocido");
		}

		detail.setStock(stock);
		detail.setQuantity(quantity);
		detail.setUnitPrice(unitPrice);
		detail.setTotalPrice(totalPrice);

		return detail;

	}

	private void updateStock(Long productId, Long stockId, Double quantity) {

		Stock stock = stockDao.findById(new StockId(stockId, productId)).orElse(null);

		stock.setQuantity(stock.getQuantity() - quantity);

		stockDao.save(stock);
	}
}
