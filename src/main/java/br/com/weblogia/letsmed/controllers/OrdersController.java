package br.com.weblogia.letsmed.controllers;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.InputStreamDownload;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.Customer;
import br.com.weblogia.letsmed.domain.NegotiationTerm;
import br.com.weblogia.letsmed.domain.Office;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderItem;
import br.com.weblogia.letsmed.domain.Product;
import br.com.weblogia.letsmed.domain.ShipmentTerm;
import br.com.weblogia.letsmed.domain.Supplier;
import br.com.weblogia.letsmed.domain.TransactionTerm;
import br.com.weblogia.letsmed.domain.UnitOfMeasure;
import br.com.weblogia.letsmed.repositories.helpers.ConnectionFactory;

@Controller
public class OrdersController {
	
	@Inject
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	private Validator validator;
	
	@Inject
	private ServletContext context;
	
	public void order(){
		Order order = new Order();
		loadLists();
		result.include("order", order);
		result.include("controller", "orders");
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/orders")
	public List<Order> list(){
		result.include("controller", "orders");
		result.include("url", "orders");
		return (List<Order>) entityManager.createQuery(" from Order o where o.conclusionDate is null order by o.id desc ").getResultList();
	}
	
	@Transactional
	public void save(Order order){
		
		if (order.getNegotiationTerm()!= null){
			order.setNegotiationTerm(entityManager.find(NegotiationTerm.class, order.getNegotiationTerm().getId()));
		}
		
		validateOrder(order);

		if(validator.hasErrors()){
			
			loadLists();
			result.include("order", order);
			validator.onErrorUsePageOf(this).order();
		}
		
		if (order.getId() == null){
			entityManager.persist(order);
			saveItens(order);
		}else{
			entityManager.merge(order);
			saveItens(order);
		}
		result.redirectTo(this).list();
	}

	private void saveItens(Order order) {
		for (OrderItem item : order.getItens()){
			if (item.getCommision() == null)
				item.setCommision(0.0);
			
			if (item.getId() == null){
				item.setOrder(order);
				entityManager.persist(item);
			}else{
				entityManager.merge(item);
			}
		}
	}

	@Get
	@Path("/orders/{id}")
	public void edit(Long id) {
		Order order = entityManager.find(Order.class, id);
		loadLists();
		result.include("order", order);
		result.include("controller", "orders");
		result.of(this).order();
	}
	
	@Get
	@Path("/orders/show/{id}")
	public void show(Long id) {
		Order order = entityManager.find(Order.class, id);
		result.include("order", order);
		result.include("controller", "orders");
	}
	
	@Get
	@Path("/orders/confirm/{id}")
	@Transactional
	public void confirm(Long id) {
		Order order = entityManager.find(Order.class, id);
		order.setConfirmationDate(new Date());
		entityManager.merge(order);
		
		result.include("controller", "orders");
		result.redirectTo(TimelineController.class).timeline();
	}
	
	@Transactional
	@Path("/orders/delete/item/{id}")
	public void deleteItem(Long id){
		if (id == null)
			return;
		OrderItem item = entityManager.find(OrderItem.class, id);
		entityManager.remove(item);
		result.nothing();
	}
	
	@Get
	@Path("/orders/print/proforma/{id}")
	public InputStreamDownload printProforma(Long id) {
		
		String caminho = context.getRealPath("/") + "WEB-INF/reports/";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order_id",id);
		params.put("SUBREPORT_DIR",caminho);
		
		byte[] pdf = null;
		ConnectionFactory factory = new ConnectionFactory(entityManager);
		
		try {
			Connection conn = factory.criarConnection();
			JasperPrint print = JasperFillManager.fillReport(caminho+"proforma-invoice.jasper", params, conn);
			pdf = JasperExportManager.exportReportToPdf(print);
			
			
			ByteArrayInputStream docInput = new ByteArrayInputStream(pdf);
			String contentType = "application/pdf";
			return new InputStreamDownload(docInput, contentType, "proforma-invoice_"+id+".pdf", false, pdf.length);
		} 
		catch (JRException e) {
			e.printStackTrace();
			validator.add(new I18nMessage("nome", "order.print.proforma"));
			validator.onErrorUsePageOf(this).edit(id);
			return null;
		}finally{
			factory.closeConnection();
		}
	}
	
	@Get
	@Path("/orders/print/comercial/{id}")
	public InputStreamDownload printComercialInvoice(Long id) {
		
		String caminho = context.getRealPath("/") + "WEB-INF/reports/";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order_id",id);
		params.put("SUBREPORT_DIR",caminho);
		
		byte[] pdf = null;
		ConnectionFactory factory = new ConnectionFactory(entityManager);
		
		try {
			Connection conn = factory.criarConnection();
			JasperPrint print = JasperFillManager.fillReport(caminho+"comercial-invoice.jasper", params, conn);
			pdf = JasperExportManager.exportReportToPdf(print);
			
			
			ByteArrayInputStream docInput = new ByteArrayInputStream(pdf);
			String contentType = "application/pdf";
			return new InputStreamDownload(docInput, contentType, "comercial-invoice_"+id+".pdf", false, pdf.length);
		} 
		catch (JRException e) {
			e.printStackTrace();
			validator.add(new I18nMessage("nome", "order.print.comercial"));
			validator.onErrorUsePageOf(this).edit(id);
			return null;
		}finally{
			factory.closeConnection();
		}
	}
	
	@Get
	@Path("/orders/print/packing/{id}")
	public InputStreamDownload printPackintList(Long id) {
		
		String caminho = context.getRealPath("/") + "WEB-INF/reports/";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order_id",id);
		params.put("SUBREPORT_DIR",caminho);
		
		byte[] pdf = null;
		ConnectionFactory factory = new ConnectionFactory(entityManager);
		
		try {
			Connection conn = factory.criarConnection();
			JasperPrint print = JasperFillManager.fillReport(caminho+"packing-list.jasper", params, conn);
			pdf = JasperExportManager.exportReportToPdf(print);
			
			ByteArrayInputStream docInput = new ByteArrayInputStream(pdf);
			String contentType = "application/pdf";
			return new InputStreamDownload(docInput, contentType, "packing-list_"+id+".pdf", false, pdf.length);
		} 
		catch (JRException e) {
			e.printStackTrace();
			validator.add(new I18nMessage("nome", "order.print.comercial"));
			validator.onErrorUsePageOf(this).edit(id);
			return null;
		}finally{
			factory.closeConnection();
		}
	}
	
	@Get
	@Path("/orders/print/purchase/{id}")
	public InputStreamDownload printPurchaseOrder(Long id) {
		
		String caminho = context.getRealPath("/") + "WEB-INF/reports/";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order_id",id);
		params.put("SUBREPORT_DIR",caminho);
		
		byte[] pdf = null;
		ConnectionFactory factory = new ConnectionFactory(entityManager);
		
		try {
			Connection conn = factory.criarConnection();
			JasperPrint print = JasperFillManager.fillReport(caminho+"purchase-order.jasper", params, conn);
			pdf = JasperExportManager.exportReportToPdf(print);
			
			ByteArrayInputStream docInput = new ByteArrayInputStream(pdf);
			String contentType = "application/pdf";
			return new InputStreamDownload(docInput, contentType, "purchase-order_"+id+".pdf", false, pdf.length);
		} 
		catch (JRException e) {
			e.printStackTrace();
			validator.add(new I18nMessage("nome", "order.print.comercial"));
			validator.onErrorUsePageOf(this).edit(id);
			return null;
		}finally{
			factory.closeConnection();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<Product> productList = (List<Product>) entityManager.createQuery(" from Product p order by p.description ").getResultList();
		List<Customer> customerList = (List<Customer>) entityManager.createQuery(" from Customer c order by c.name ").getResultList();
		List<Supplier> supplierList = (List<Supplier>) entityManager.createQuery(" from Supplier s order by s.supplierName ").getResultList();
		List<TransactionTerm> transactionTermList = (List<TransactionTerm>) entityManager.createQuery(" from TransactionTerm t order by t.description ").getResultList();
		List<NegotiationTerm> negotiationTermList = (List<NegotiationTerm>) entityManager.createQuery(" from NegotiationTerm p order by p.description ").getResultList();
		List<UnitOfMeasure> unitsList = (List<UnitOfMeasure>) entityManager.createQuery(" from UnitOfMeasure u order by u.description ").getResultList();
		List<ShipmentTerm> shipmentTermList = (List<ShipmentTerm>) entityManager.createQuery(" from ShipmentTerm s order by s.description ").getResultList();
		List<Office> officeList = (List<Office>) entityManager.createQuery(" from Office o order by o.officeName ").getResultList();
		result.include("officeList", officeList);
		result.include("productList", productList);
		result.include("customerList", customerList);
		result.include("supplierList", supplierList);
		result.include("transactionTermList", transactionTermList);
		result.include("negotiationTermList", negotiationTermList);
		result.include("shipmentTermList", shipmentTermList);
		result.include("unitsList", unitsList);
	}
	
	private void validateOrder(Order order) {
		if (order.getCustomer().getId() == -1) 	order.setCustomer(null);
		if (order.getSupplier().getId() == -1) 	order.setSupplier(null);
		if (order.getTransactionTerm().getId() == -1) 	order.setTransactionTerm(null);
		if (order.getNegotiationTerm().getId() == -1) 	order.setNegotiationTerm(null);
		if (order.getShipmentTerm().getId() == -1) 	order.setShipmentTerm(null);
		
		for (OrderItem item : order.getItens()){
			if (item.getProduct().getId() == -1) item.setProduct(null);
			validator.addIf( item.getProduct() == null,new I18nMessage("ord","item.without.product"));
			validator.addIf( item.getUnitPrice() == null,new I18nMessage("ord","order.without.price"));
			validator.addIf( item.getQuantity() == null,new I18nMessage("ord","order.without.quantity"));
			if (order.getTransactionTerm().getId().intValue() != 3){
				validator.addIf(item.getCommision() == null || item.getCommision().doubleValue() == 0.0, new I18nMessage("ord","item.without.commision"));
			}
		}
		
		validator.addIf( order.getOrderDate() == null,new I18nMessage("cus","order.without.date"));
		validator.addIf( order.getCustomer() == null,new I18nMessage("cus","order.without.customer"));
		validator.addIf( order.getTransactionTerm() == null,new I18nMessage("cus","order.without.transaction"));
		validator.addIf( order.getNegotiationTerm() == null,new I18nMessage("cus","order.without.negotiation"));
	}
}
