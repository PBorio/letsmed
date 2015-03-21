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
import br.com.weblogia.letsmed.domain.ExpenseCategory;
import br.com.weblogia.letsmed.domain.Partner;

@Controller
public class PartnersController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void partner(){
		loadLists();
		result.include("controller", "partners");
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/partners")
	public List<Partner> list(){
		result.include("controller", "partners");
		return (List<Partner>) entityManager.createQuery(" from Partner o order by o.partnerName ").getResultList();
	}
	
	@Transactional
	public void save(Partner partner){
		
		validator.addIf(( partner.getPartnerName() == null || partner.getPartnerName().trim().equals("")),new I18nMessage("off","partner.without.name"));

		if(validator.hasErrors()){
			loadLists();
			result.include("partner", partner);
			validator.onErrorUsePageOf(this).partner();
		}
		
		if (partner.getExpenseAccount().getId() == -1)
			partner.setExpenseAccount(null);
		
		if (partner.getId() == null){
			entityManager.persist(partner);
		}else{
			entityManager.merge(partner);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/partners/{id}")
	public void edit(Long id) {
		Partner partner = entityManager.find(Partner.class, id);
		loadLists();
		result.include("controller", "partners");
		result.include("partner", partner);
		result.of(this).partner();
	}
	
	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
		List<ExpenseCategory> expenseCategoryList = (List<ExpenseCategory>) entityManager.createQuery(" from ExpenseCategory ec order by ec.description ").getResultList();
		result.include("expenseAccountList", expenseAccountList);
		result.include("expenseCategoryList", expenseCategoryList);
	}

}
