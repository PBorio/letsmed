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
import br.com.weblogia.letsmed.domain.Expense;
import br.com.weblogia.letsmed.domain.ExpenseAccount;
import br.com.weblogia.letsmed.domain.ExpenseCategory;
import br.com.weblogia.letsmed.domain.Office;

@Controller
public class ExpensesController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void expense(){
		loadLists();
		result.include("controller", "expensePayments");
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/expenses")
	public void list(){
		result.include("controller", "expensePayments");
		List<Expense> expenseList = (List<Expense>) entityManager.createQuery(" from Expense e order by e.expenseDate desc").getResultList();
		result.include("expenseList", expenseList);
	}
	
	@Transactional
	@Post
	@Path("/expenses/save")
	public void save(Expense expense){
		
		validateExpense(expense);

		if(validator.hasErrors()){
			loadLists();
			result.include("expense", expense);
			validator.onErrorUsePageOf(this).expense();
		}
		
		if (expense.getId() == null){
			entityManager.persist(expense);
		}else{
			entityManager.merge(expense);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/expenses/{id}")
	public void editPayment(Long id) {
		Expense expense = entityManager.find(Expense.class, id);
		loadLists();
		result.include("expense", expense);
		result.include("controller", "expensePayments");
		result.of(this).expense();
	}
	
	private void validateExpense(Expense expense) {
		if (expense.getExpenseAccount().getId() == -1) 	expense.setExpenseAccount(null);
		if (expense.getExpenseCategory().getId() == -1) expense.setExpenseCategory(null);
		
		validator.addIf( expense.getExpenseDate() == null,new I18nMessage("rev","expense.without.date"));
		validator.addIf( expense.getValue() == null,new I18nMessage("cus","expense.without.value"));
		validator.addIf( expense.getExpenseAccount() == null,new I18nMessage("cus","expense.without.account"));
		validator.addIf( expense.getExpenseCategory() == null,new I18nMessage("cus","expense.without.category"));
	}


	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
		List<Office> officeList = (List<Office>) entityManager.createQuery(" from Office o order by o.officeName ").getResultList();
		List<ExpenseCategory> expenseCategoryList = (List<ExpenseCategory>) entityManager.createQuery(" from ExpenseCategory ec order by ec.description ").getResultList();
		result.include("expenseAccountList", expenseAccountList);
		result.include("officeList", officeList);
		result.include("expenseCategoryList", expenseCategoryList);
	}

}
