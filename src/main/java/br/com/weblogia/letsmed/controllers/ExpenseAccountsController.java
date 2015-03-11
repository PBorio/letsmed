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
import br.com.weblogia.letsmed.domain.ExpenseAccount;

@Controller
public class ExpenseAccountsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void account(){
		result.include("controller", "expenseAccounts");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/expenseAccounts")
	public List<ExpenseAccount> list(){
		result.include("controller", "expenseAccounts");
		return (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
	}
	
	@Transactional
	public void save(ExpenseAccount expenseAccount){
		
		validator.addIf(( expenseAccount.getDescription() == null || expenseAccount.getDescription().trim().equals("")),new I18nMessage("rev","account.without.description"));

		if(validator.hasErrors()){
			result.include("expenseAccount", expenseAccount);
			validator.onErrorUsePageOf(this).account();
		}
		
		if (expenseAccount.getId() == null){
			entityManager.persist(expenseAccount);
		}else{
			entityManager.merge(expenseAccount);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/expenseAccounts/{id}")
	public void edit(Long id) {
		ExpenseAccount expenseAccount = entityManager.find(ExpenseAccount.class, id);
		result.include("controller", "expenseAccounts");
		result.include("expenseAccount", expenseAccount);
		result.of(this).account();
	}

}
