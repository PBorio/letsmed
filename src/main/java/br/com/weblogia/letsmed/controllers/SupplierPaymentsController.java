package br.com.weblogia.letsmed.controllers;

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
import br.com.weblogia.domain.OrderSupplierPayment;
import br.com.weblogia.letsmed.domain.Order;

@Controller
public class SupplierPaymentsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Get
	@Path("/supplierPayments/order/{id}")
	public void payment(Long id){
		Order order = entityManager.find(Order.class, id);
		OrderSupplierPayment supplierPayment = new OrderSupplierPayment();
		supplierPayment.setOrder(order);
		loadLists();
		result.include(order);
		result.include("supplierPayment", supplierPayment);
	}

	@Transactional
	public void save(OrderSupplierPayment supplierPayment){
		
		validatePayment(supplierPayment);

		if(validator.hasErrors()){
			loadLists();
			result.include("supplierPayment", supplierPayment);
			validator.onErrorUsePageOf(this).payment(null);
		}
		
		if (supplierPayment.getId() == null){
			entityManager.persist(supplierPayment);
		}else{
			entityManager.merge(supplierPayment);
		}
		result.redirectTo(this).payment(supplierPayment.getOrder().getId());
	}

	@Get
	@Path("/supplierPayments/{id}")
	public void edit(Long id) {
		OrderSupplierPayment supplierPayment = entityManager.find(OrderSupplierPayment.class, id);
		loadLists();
		result.include("supplierPayment", supplierPayment);
		result.include("order", supplierPayment.getOrder());
		result.of(this).payment(null);
	}
	
	@Path("/supplierPayments/delete/{id}")
	public void deleteItem(Long id){
		if (id == null)
			return;
		OrderSupplierPayment payment = entityManager.find(OrderSupplierPayment.class, id);
		Long orderId = payment.getOrder().getId();
		entityManager.remove(payment);
		result.redirectTo(this).payment(orderId);
	}

	private void loadLists() {
//		List<TransactionTerms> paymentTermList = (List<TransactionTerms>) entityManager.createQuery(" from PaymentTerm p order by p.description ").getResultList();
//		result.include("paymentTermList", paymentTermList);
	}
	
	private void validatePayment(OrderSupplierPayment supplierPayment) {
		validator.addIf( supplierPayment.getPaymentDate() == null,new I18nMessage("rev","revenuepayment.without.date"));
		validator.addIf( supplierPayment.getValue() == null,new I18nMessage("cus","revenuepayment.without.value"));
	}

}
