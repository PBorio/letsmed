package br.com.weblogia.letsmed.controllers;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.ExpenseAccount;
import br.com.weblogia.letsmed.domain.Supplier;
import br.com.weblogia.letsmed.domain.User;

@Controller
public class SuppliersController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void supplier(){
		loadList();
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/suppliers")
	public List<Supplier> list(){
		result.include("controller", "suppliers");
		return (List<Supplier>) entityManager.createQuery(" from Supplier s order by s.supplierName ").getResultList();
	}
	
	@Transactional
	public void save(Supplier supplier){
		
		validator.addIf(( supplier.getSupplierName() == null || supplier.getSupplierName().trim().equals("")),new I18nMessage("off","supplier.without.name"));

		if(validator.hasErrors()){
			loadList();
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
	
	@Get
	@Path("/suppliers/{id}")
	public void edit(Long id) {
		Supplier supplier = entityManager.find(Supplier.class, id);
		loadList();
		result.include("controller", "suppliers");
		result.include("supplier", supplier);
		result.of(this).supplier();
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/suppliers/searchByName.json")
	public void buscarClientesPorNome(String term) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Supplier s ");
		sql.append(" where 1 = 1 ");
			sql.append(" and s.supplierName like :name");
		
		Query q = entityManager.createQuery(sql.toString());
		
		q.setParameter("name", "%"+term+"%");
		result.use(json()).withoutRoot()
				.from((List<Supplier>)q.getResultList()).recursive()
				.serialize();
	}
	
	
	@SuppressWarnings("unchecked")
	private void loadList() {
		List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
		List<User> userList = (List<User>) entityManager.createQuery(" from User u order by u.name ").getResultList();
		
		result.include("expenseAccountList", expenseAccountList);
		result.include("userList", userList);
	}

}
