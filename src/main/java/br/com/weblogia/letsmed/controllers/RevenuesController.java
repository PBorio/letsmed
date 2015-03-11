package br.com.weblogia.letsmed.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.PaymentTerm;
import br.com.weblogia.letsmed.domain.Revenue;
import br.com.weblogia.letsmed.domain.RevenuePayment;

@Controller
public class RevenuesController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Get
	@Path("/revenues/payment/{id}")
	public void payment(Long id){
		Revenue revenue = entityManager.find(Revenue.class, id);
		RevenuePayment revenuePayment = new RevenuePayment();
		revenuePayment.setRevenue(revenue);
		loadLists();
		result.include("revenue", revenue);
		result.include("revenuePayment", revenuePayment);
		result.include("controller", "revenuePayments");
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/revenues")
	public void list(){
		result.include("controller", "revenuePayments");
		List<Revenue> revenueList = (List<Revenue>) entityManager.createQuery(" from Revenue r order by r.revenueDate desc").getResultList();
		result.include("revenueList", revenueList);
	}
	
	@Transactional
	@Post
	@Path("/revenues/payment/save")
	public void save(RevenuePayment revenuePayment){
		
		validatePayment(revenuePayment);

		if(validator.hasErrors()){
			loadLists();
			result.include("revenuePayment", revenuePayment);
			validator.onErrorUsePageOf(this).payment(null);
		}
		
		if (revenuePayment.getId() == null){
			entityManager.persist(revenuePayment);
		}else{
			entityManager.merge(revenuePayment);
		}
		result.redirectTo(this).payment(revenuePayment.getRevenue().getId());
	}

	@Get
	@Path("/revenues/payment/edit/{id}")
	public void editPayment(Long id) {
		RevenuePayment revenuePayment = entityManager.find(RevenuePayment.class, id);
		loadLists();
		result.include("revenuePayment", revenuePayment);
		result.include("revenue", revenuePayment.getRevenue());
		result.include("controller", "revenuePayments");
		result.of(this).payment(null);
	}
	
	@Path("/revenues/payment/delete/{id}")
	public void deleteItem(Long id){
		if (id == null)
			return;
		RevenuePayment payment = entityManager.find(RevenuePayment.class, id);
		Long revenueId = payment.getRevenue().getId();
		entityManager.remove(payment);
		result.redirectTo(this).payment(revenueId);
	}

	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<PaymentTerm> paymentTermList = (List<PaymentTerm>) entityManager.createQuery(" from PaymentTerm p order by p.description ").getResultList();
		result.include("paymentTermList", paymentTermList);
	}
	
	private void validatePayment(RevenuePayment revenuePayment) {
		if (revenuePayment.getRevenue().getId() == -1) 	revenuePayment.setRevenue(null);
		if (revenuePayment.getPaymentTerm().getId() == -1) 	revenuePayment.setPaymentTerm(null);
		
		validator.addIf( revenuePayment.getPaymentDate() == null,new I18nMessage("rev","revenuepayment.without.date"));
		validator.addIf( revenuePayment.getValue() == null,new I18nMessage("cus","revenuepayment.without.value"));
	}

}
