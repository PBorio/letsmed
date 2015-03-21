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
import br.com.weblogia.letsmed.domain.BankAccount;

@Controller
public class BankAccountsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void bankAccount(){
		result.include("controller", "expenseCategories");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/bankAccounts")
	public List<BankAccount> list(){
		result.include("controller", "banks");
		return (List<BankAccount>) entityManager.createQuery(" from BankAccount b order by b.description ").getResultList();
	}
	
	@Transactional
	public void save(BankAccount bankAccount){
		
		validator.addIf(( bankAccount.getDescription() == null || bankAccount.getDescription().trim().equals("")),new I18nMessage("bak","bank.without.description"));
		validator.addIf( bankAccount.getBalance() == null ,new I18nMessage("bak","bank.without.balance"));

		if(validator.hasErrors()){
			result.include("bankAccount", bankAccount);
			result.include("controller", "bankAccounts");
			validator.onErrorUsePageOf(this).bankAccount();
		}
		
		if (bankAccount.getId() == null){
			entityManager.persist(bankAccount);
		}else{
			entityManager.merge(bankAccount);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/bankAccounts/{id}")
	public void edit(Long id) {
		BankAccount bankAccount = entityManager.find(BankAccount.class, id);
		result.include("controller", "bankAccounts");
		result.include("bankAccount", bankAccount);
		result.of(this).bankAccount();
	}

}
