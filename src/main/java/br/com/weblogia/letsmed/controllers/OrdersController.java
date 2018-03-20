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
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
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
import br.com.weblogia.letsmed.domain.User;
import br.com.weblogia.letsmed.domain.service.OrderService;
import br.com.weblogia.letsmed.repositories.helpers.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
	
	@Inject 
	private User user;
	
	public void order(){
		Order order = new Order();
		order.setOrderDate(new Date());
		loadLists();
		result.include("order", order);
		result.include("controller", "orders");
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/orders")
	public List<Order> list(){
		result.include("url", "orders");
		
		StringBuilder hql = new StringBuilder();
		hql.append(" from Order o");
		hql.append(" inner join fetch o.supplier s ");
		
		if (!user.isAdmin()) {
			hql.append(" where s.user.id = :id ");
		}
		
		Query q = entityManager.createQuery(hql.toString());
		if (!user.isAdmin()) {
			q.setParameter("id", user.getId());
		}
		
		return (List<Order>) q.getResultList();
	}
	
	@Transactional
	public void save(Order order){
		
		validateOrder(order);

		if(validator.hasErrors()){
			loadLists();
			result.include("order", order);
			validator.onErrorUsePageOf(this).order();
		}
		
		boolean novo = (order.getId() == null);
		try{
			OrderService orderService = new OrderService(entityManager);
			orderService.save(order);
		}catch(RuntimeException e){
			validator.add(new I18nMessage("ord", e.getMessage()));
			if (validator.hasErrors()){
				loadLists();
				result.include("order", order);
				validator.onErrorUsePageOf(this).order();
			}
		}
		
		if (novo) 
			result.redirectTo(this).item(order.getId()); 
		else
			result.redirectTo(this).edit(order.getId()) ; 
	}
	
	@Transactional
	@Post
	@Path("/orders/item/save")
	public void saveItem(OrderItem item){
		
		if (item.getProduct() != null)
			if (item.getProduct().getId() == null || item.getProduct().getId() == -1) item.setProduct(null);
		
		if (item.getUnitOfMeasure() != null)
			if (item.getUnitOfMeasure().getId() == null || item.getUnitOfMeasure().getId() == -1) item.setUnitOfMeasure(null);
		
		validator.addIf( (item.getProduct() == null) && (item.getAdditionalDescription() == null || "".equals(item.getAdditionalDescription().trim())),new I18nMessage("ord","item.without.product"));
		validator.addIf( (item.getProduct() != null) && (item.getUnitOfMeasure() == null),new I18nMessage("ord","item.without.unit"));
		validator.addIf( item.getUnitPrice() == null,new I18nMessage("ord","item.without.price"));
		validator.addIf( item.getQuantity() == null,new I18nMessage("ord","item.without.quantity"));
		
		if (item.getCommision() == null)
			item.setCommision(0.0);
		
		if(validator.hasErrors()){
			loadLists();
			result.include("item", item);
			result.include("order", item.getOrder());
			validator.onErrorUsePageOf(this).item(item.getOrder().getId());
		}
		
		if (item.getProduct() != null){
			Product p = entityManager.find(Product.class, item.getProduct().getId());
			p.setBuyPrice(item.getBuyPrice());
			p.setSellPrice(item.getUnitPrice());
			p.setCommision(item.getCommision());
			entityManager.merge(p);
		}
		
		if (item.getId() == null){
			entityManager.persist(item);
			result.redirectTo(this).item(item.getOrder().getId());
		}else{
			entityManager.merge(item);
			result.redirectTo(this).edit(item.getOrder().getId());
		}
		
		
	}

	@Get
	@Path("/orders/item/{id}")
	public void item(Long id) {
		Order order = entityManager.find(Order.class, id);
		
		OrderItem item = new OrderItem();
		item.setOrder(order);
		
		loadLists();
		result.include("item", item);
		result.include("order", order);
	}
	
	@Get
	@Path("/orders/edit/item/{id}")
	public void editItem(Long id) {
		OrderItem item = entityManager.find(OrderItem.class, id);
		loadLists();
		result.include("order", item.getOrder());
		result.include("item", item);
		result.of(this).item(id);
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
		
		validator.addIf( order.getItens().size() == 0,new I18nMessage("cus","order.without.products"));
		if(validator.hasErrors()){
			loadLists();
			result.include("order", order);
			validator.onErrorUsePageOf(this).order();
		}
		
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
	public void search(Customer customer) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" where 1 = 1 ");
		sql.append(" and o.customer.id = :id ");
		
		Query q = entityManager.createQuery(sql.toString());
		
		q.setParameter("id", customer.getId());
		result.include("orderList", (List<Customer>)q.getResultList());
		result.include("customer", customer);
		result.include("url", "orders");
		result.of(this).list();
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
		
		if (order.getNegotiationTerm()!= null && order.getNegotiationTerm().getId().intValue() != -1){
			order.setNegotiationTerm(entityManager.find(NegotiationTerm.class, order.getNegotiationTerm().getId()));
		}
		
		if (order.getCustomer().getId() == -1) 	order.setCustomer(null);
		if (order.getSupplier().getId() == -1) 	order.setSupplier(null);
		if (order.getTransactionTerm().getId() == -1) 	order.setTransactionTerm(null);
		if (order.getNegotiationTerm().getId() == -1) 	order.setNegotiationTerm(null);
		if (order.getShipmentTerm().getId() == -1) 	order.setShipmentTerm(null);
		
		for (OrderItem item : order.getItens()){
			if (item.getProduct().getId() == null || item.getProduct().getId() == -1) item.setProduct(null);
			validator.addIf( item.getProduct() == null,new I18nMessage("ord","item.without.product"));
			validator.addIf( item.getUnitPrice() == null,new I18nMessage("ord","order.without.price"));
			validator.addIf( item.getQuantity() == null,new I18nMessage("ord","order.without.quantity"));
		}
		
		validator.addIf( order.getOrderDate() == null,new I18nMessage("cus","order.without.date"));
		validator.addIf( order.getCustomer() == null,new I18nMessage("cus","order.without.customer"));
		validator.addIf( order.getTransactionTerm() == null,new I18nMessage("cus","order.without.transaction"));
		validator.addIf( order.getNegotiationTerm() == null,new I18nMessage("cus","order.without.negotiation"));
		validator.addIf( order.getOrderNumber() == null,new I18nMessage("cus","order.without.number"));
	}
}
