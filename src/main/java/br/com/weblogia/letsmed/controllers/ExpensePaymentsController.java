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
import br.com.weblogia.letsmed.domain.BankAccount;
import br.com.weblogia.letsmed.domain.ExpenseAccount;
import br.com.weblogia.letsmed.domain.ExpensePayment;
import br.com.weblogia.letsmed.domain.Office;

@Controller
public class ExpensePaymentsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/expensePayments")
	public void list(){
		result.include("controller", "expensePayments");
		List<ExpensePayment> expensePaymentList = (List<ExpensePayment>) entityManager.createQuery(" from ExpensePayment e order by e.paymentDate desc").getResultList();
		result.include("expensePaymentList", expensePaymentList);
	}
	
	@Get
	public void payment(){
		loadLists();
		result.include("controller", "expensePayments");
	}
	
	@Transactional
	@Post
	public void save(ExpensePayment expensePayment){
		
		validatePayment(expensePayment);
		if(validator.hasErrors()){
			loadLists();
			result.include("expensePayment", expensePayment);
			validator.onErrorUsePageOf(this).payment();
		}
		if (expensePayment.getId() == null){
			entityManager.persist(expensePayment);
		}else{
			entityManager.merge(expensePayment);
		}
		result.redirectTo(this).list();
	}

	@Get
	@Path("/expensePayments/{id}")
	public void editPayment(Long id) {
		ExpensePayment expensePayment = entityManager.find(ExpensePayment.class, id);
		loadLists();
		result.include("expensePayment", expensePayment);
		result.include("controller", "expensePayments");
		result.of(this).payment();
	}
	
	
	//TODO: fix delete
	@SuppressWarnings("unused")
	private void deleteItem(Long id){
		if (id == null)
			return;
		ExpensePayment payment = entityManager.find(ExpensePayment.class, id);
		entityManager.remove(payment);
		result.redirectTo(this).list();
	}
	
	private void validatePayment(ExpensePayment expensePayment) {
		if (expensePayment.getExpenseAccount().getId() == -1) 	expensePayment.setExpenseAccount(null);
		if (expensePayment.getBankAccount().getId() == -1) 	expensePayment.setBankAccount(null);
		
		validator.addIf( expensePayment.getPaymentDate() == null,new I18nMessage("rev","expensepayment.without.date"));
		validator.addIf( expensePayment.getValue() == null,new I18nMessage("cus","expensepayment.without.value"));
		validator.addIf( expensePayment.getValue() == null,new I18nMessage("cus","expensepayment.without.expense"));
		validator.addIf( expensePayment.getValue() == null,new I18nMessage("cus","expensepayment.without.bank"));
	}
	
	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
		List<Office> officeList = (List<Office>) entityManager.createQuery(" from Office o order by o.officeName ").getResultList();
		List<BankAccount> bankAccountList = (List<BankAccount>) entityManager.createQuery(" from BankAccount b order by b.description ").getResultList();
		result.include("bankAccountList", bankAccountList);
		result.include("expenseAccountList", expenseAccountList);
		result.include("officeList", officeList);
	}

}
