package br.com.weblogia.letsmed.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.Customer;
import br.com.weblogia.letsmed.domain.NegotiationTerm;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderItem;
import br.com.weblogia.letsmed.domain.PaymentTerm;
import br.com.weblogia.letsmed.domain.Product;
import br.com.weblogia.letsmed.domain.Supplier;
import br.com.weblogia.letsmed.domain.service.OrderService;

@Controller
public class OrdersController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
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
		return (List<Order>) entityManager.createQuery(" from Order o where confirmationDate is null order by o.orderDate desc ").getResultList();
	}
	
	@Transactional
	public void save(Order order){
		
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
		
		OrderService service = new OrderService(entityManager);
		service.confirm(order);
		
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

	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<Product> productList = (List<Product>) entityManager.createQuery(" from Product p order by p.description ").getResultList();
		List<Customer> customerList = (List<Customer>) entityManager.createQuery(" from Customer c order by c.name ").getResultList();
		List<Supplier> supplierList = (List<Supplier>) entityManager.createQuery(" from Supplier s order by s.supplierName ").getResultList();
		List<PaymentTerm> paymentTermList = (List<PaymentTerm>) entityManager.createQuery(" from PaymentTerm p order by p.description ").getResultList();
		List<NegotiationTerm> negotiationTermList = (List<NegotiationTerm>) entityManager.createQuery(" from NegotiationTerm p order by p.description ").getResultList();
		result.include("productList", productList);
		result.include("customerList", customerList);
		result.include("supplierList", supplierList);
		result.include("paymentTermList", paymentTermList);
		result.include("negotiationTermList", negotiationTermList);
	}
	
	private void validateOrder(Order order) {
		if (order.getCustomer().getId() == -1) 	order.setCustomer(null);
		if (order.getSupplier().getId() == -1) 	order.setSupplier(null);
		if (order.getPaymentTerm().getId() == -1) 	order.setPaymentTerm(null);
		if (order.getNegotiationTerm().getId() == -1) 	order.setNegotiationTerm(null);
		
		for (OrderItem item : order.getItens()){
			if (item.getProduct().getId() == -1) item.setProduct(null);
			validator.addIf( item.getProduct() == null,new I18nMessage("ord","item.without.product"));
			validator.addIf( item.getUnitPrice() == null,new I18nMessage("ord","order.without.price"));
			validator.addIf( item.getQuantity() == null,new I18nMessage("ord","order.without.quantity"));
		}
		
		validator.addIf( order.getOrderDate() == null,new I18nMessage("cus","order.without.date"));
		validator.addIf( order.getCustomer() == null,new I18nMessage("cus","order.without.customer"));
		validator.addIf( order.getPaymentTerm() == null,new I18nMessage("cus","order.without.payment"));
		validator.addIf( order.getNegotiationTerm() == null,new I18nMessage("cus","order.without.negotiation"));
	}
}
