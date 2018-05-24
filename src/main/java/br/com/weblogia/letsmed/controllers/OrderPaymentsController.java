package br.com.weblogia.letsmed.controllers;

import java.util.Date;

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
		orderPayment.setPaymentDate(new Date());
		orderPayment.setOrder(order);
		result.include(order);
		result.include("orderPayment", orderPayment);
		result.include("controller", "orderPayments");
	}

	@Transactional
	public void save(OrderPayment orderPayment){
		
		validatePayment(orderPayment);

		if(validator.hasErrors()){
			result.include("orderPayment", orderPayment);
			validator.onErrorUsePageOf(this).payment(null);
		}
		
		if (orderPayment.getId() == null){
			entityManager.persist(orderPayment);
		}else{
			entityManager.merge(orderPayment);
		}
		result.redirectTo(TimelineController.class).timeline();
	}

	@Get
	@Path("/orderPayments/{id}")
	public void edit(Long id) {
		OrderPayment orderPayment = entityManager.find(OrderPayment.class, id);
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

	private void validatePayment(OrderPayment orderPayment) {
		validator.addIf( orderPayment.getPaymentDate() == null,new I18nMessage("rev","revenuepayment.without.date"));
		validator.addIf( orderPayment.getValue() == null,new I18nMessage("cus","revenuepayment.without.value"));
	}

}
