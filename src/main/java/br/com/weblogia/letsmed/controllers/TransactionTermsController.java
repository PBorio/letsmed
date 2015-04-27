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
import br.com.weblogia.letsmed.domain.TransactionTerm;

@Controller
public class TransactionTermsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void term(){
		result.include("controller", "transactionTerms");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/transactionTerms")
	public List<TransactionTerm> list(){
		result.include("controller", "transactionTerms");
		return (List<TransactionTerm>) entityManager.createQuery(" from TransactionTerm t order by t.description ").getResultList();
	}
	
	@Transactional
	public void save(TransactionTerm transactionTerm){
		
		validator.addIf(( transactionTerm.getDescription() == null || transactionTerm.getDescription().trim().equals("")),new I18nMessage("rev","term.without.description"));

		if(validator.hasErrors()){
			result.include("transactionTerm", transactionTerm);
			validator.onErrorUsePageOf(this).term();
		}
		
		if (transactionTerm.getId() == null){
			entityManager.persist(transactionTerm);
		}else{
			entityManager.merge(transactionTerm);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/transactionTerms/{id}")
	public void edit(Long id) {
		TransactionTerm transactionTerm = entityManager.find(TransactionTerm.class, id);
		result.include("controller", "transactionTerms");
		result.include("transactionTerm", transactionTerm);
		result.of(this).term();
	}

}
