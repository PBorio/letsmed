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
import br.com.weblogia.letsmed.domain.ExpenseCategory;

@Controller
public class ExpenseCategoriesController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void category(){
		result.include("controller", "expenseCategories");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/expenseCategories")
	public List<ExpenseCategory> list(){
		result.include("controller", "expenseCategories");
		return (List<ExpenseCategory>) entityManager.createQuery(" from ExpenseCategory ec order by ec.description ").getResultList();
	}
	
	@Transactional
	public void save(ExpenseCategory expenseCategory){
		
		validator.addIf(( expenseCategory.getDescription() == null || expenseCategory.getDescription().trim().equals("")),new I18nMessage("rev","category.without.description"));

		if(validator.hasErrors()){
			result.include("expenseCategory", expenseCategory);
			result.include("controller", "expenseCategories");
			validator.onErrorUsePageOf(this).category();
		}
		
		if (expenseCategory.getId() == null){
			entityManager.persist(expenseCategory);
		}else{
			entityManager.merge(expenseCategory);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/expenseAccounts/{id}")
	public void edit(Long id) {
		ExpenseCategory expenseCategory = entityManager.find(ExpenseCategory.class, id);
		result.include("controller", "expenseCategories");
		result.include("expenseCategory", expenseCategory);
		result.of(this).category();
	}

}
