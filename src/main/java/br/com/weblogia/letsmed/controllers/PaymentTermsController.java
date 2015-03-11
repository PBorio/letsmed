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
import br.com.weblogia.letsmed.domain.PaymentTerm;

@Controller
public class PaymentTermsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void term(){
		result.include("controller", "paymentTerms");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/paymentTerms")
	public List<PaymentTerm> list(){
		result.include("controller", "paymentTerms");
		return (List<PaymentTerm>) entityManager.createQuery(" from PaymentTerm ea order by ea.description ").getResultList();
	}
	
	@Transactional
	public void save(PaymentTerm paymentTerm){
		
		validator.addIf(( paymentTerm.getDescription() == null || paymentTerm.getDescription().trim().equals("")),new I18nMessage("rev","term.without.description"));

		if(validator.hasErrors()){
			result.include("paymentTerm", paymentTerm);
			validator.onErrorUsePageOf(this).term();
		}
		
		if (paymentTerm.getId() == null){
			entityManager.persist(paymentTerm);
		}else{
			entityManager.merge(paymentTerm);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/paymentTerms/{id}")
	public void edit(Long id) {
		PaymentTerm paymentTerm = entityManager.find(PaymentTerm.class, id);
		result.include("controller", "paymentTerms");
		result.include("paymentTerm", paymentTerm);
		result.of(this).term();
	}

}
