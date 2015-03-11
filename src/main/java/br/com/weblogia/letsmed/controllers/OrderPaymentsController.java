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
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderPayment;
import br.com.weblogia.letsmed.domain.PaymentTerm;

@Controller
public class OrderPaymentsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Get
	@Path("/orderPayments/order/{id}")
	public void payment(Long id){
		Order order = entityManager.find(Order.class, id);
		OrderPayment orderPayment = new OrderPayment();
		orderPayment.setOrder(order);
		loadLists();
		result.include(order);
		result.include("orderPayment", orderPayment);
		result.include("controller", "orderPayments");
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/orderPayments")
	public void list(){
		result.include("controller", "orderPayments");
		List<Order> orderList = (List<Order>) entityManager.createQuery(" from Order o where confirmationDate is not null and originalDocumentDate is null order by o.orderDate desc").getResultList();
		result.include("orderList", orderList);
		result.include("url", "orderPayments/order");
		result.of(OrdersController.class).list();
	}
	
	@Transactional
	public void save(OrderPayment orderPayment){
		
		validatePayment(orderPayment);

		if(validator.hasErrors()){
			loadLists();
			result.include("orderPayment", orderPayment);
			validator.onErrorUsePageOf(this).payment(null);
		}
		
		if (orderPayment.getId() == null){
			entityManager.persist(orderPayment);
		}else{
			entityManager.merge(orderPayment);
		}
		result.redirectTo(this).payment(orderPayment.getOrder().getId());
	}

	@Get
	@Path("/orderPayments/{id}")
	public void edit(Long id) {
		OrderPayment orderPayment = entityManager.find(OrderPayment.class, id);
		loadLists();
		result.include("orderPayment", orderPayment);
		result.include("order", orderPayment.getOrder());
		result.include("controller", "orderPayments");
		result.of(this).payment(null);
	}
	
	@Path("/orderPayments/delete/{id}")
	public void deleteItem(Long id){
		if (id == null)
			return;
		OrderPayment payment = entityManager.find(OrderPayment.class, id);
		Long orderId = payment.getOrder().getId();
		entityManager.remove(payment);
		result.redirectTo(this).payment(orderId);
	}

	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<PaymentTerm> paymentTermList = (List<PaymentTerm>) entityManager.createQuery(" from PaymentTerm p order by p.description ").getResultList();
		result.include("paymentTermList", paymentTermList);
	}
	
	private void validatePayment(OrderPayment orderPayment) {
		if (orderPayment.getPaymentTerm().getId() == -1) 	orderPayment.setPaymentTerm(null);
		
		validator.addIf( orderPayment.getPaymentDate() == null,new I18nMessage("rev","revenuepayment.without.date"));
		validator.addIf( orderPayment.getValue() == null,new I18nMessage("cus","revenuepayment.without.value"));
	}

}
