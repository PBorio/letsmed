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
import br.com.weblogia.letsmed.domain.Supplier;

@Controller
public class SuppliersController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@SuppressWarnings("unchecked")
	public void supplier(){
		List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
		result.include("expenseAccountList", expenseAccountList);
		result.include("controller", "suppliers");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/suppliers")
	public List<Supplier> list(){
		result.include("controller", "suppliers");
		return (List<Supplier>) entityManager.createQuery(" from Supplier s order by s.supplierName ").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void save(Supplier supplier){
		
		validator.addIf(( supplier.getSupplierName() == null || supplier.getSupplierName().trim().equals("")),new I18nMessage("off","supplier.without.name"));

		if(validator.hasErrors()){
			List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
			result.include("expenseAccountList", expenseAccountList);
			result.include("partner", supplier);
			validator.onErrorUsePageOf(this).supplier();
		}
		
		if (supplier.getExpenseAccount().getId() == null || supplier.getExpenseAccount().getId() == -1)
			supplier.setExpenseAccount(null);
		
		if (supplier.getId() == null){
			entityManager.persist(supplier);
		}else{
			entityManager.merge(supplier);
		}
		result.redirectTo(this).list();
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/suppliers/{id}")
	public void edit(Long id) {
		Supplier supplier = entityManager.find(Supplier.class, id);
		List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
		result.include("expenseAccountList", expenseAccountList);
		result.include("controller", "suppliers");
		result.include("supplier", supplier);
		result.of(this).supplier();
	}

}
