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
import br.com.weblogia.letsmed.domain.RevenueAccount;

@Controller
public class RevenueAccountsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void account(){
		result.include("controller", "revenueAccounts");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/revenueAccounts")
	public List<RevenueAccount> list(){
		result.include("controller", "revenueAccounts");
		return (List<RevenueAccount>) entityManager.createQuery(" from RevenueAccount ra order by ra.description ").getResultList();
	}
	
	@Transactional
	public void save(RevenueAccount revenueAccount){
		
		validator.addIf(( revenueAccount.getDescription() == null || revenueAccount.getDescription().trim().equals("")),new I18nMessage("rev","account.without.description"));

		if(validator.hasErrors()){
			result.include("revenueAccount", revenueAccount);
			validator.onErrorUsePageOf(this).account();
		}
		
		if (revenueAccount.getId() == null){
			entityManager.persist(revenueAccount);
		}else{
			entityManager.merge(revenueAccount);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/revenueAccounts/{id}")
	public void edit(Long id) {
		RevenueAccount revenueAccount = entityManager.find(RevenueAccount.class, id);
		result.include("controller", "revenueAccounts");
		result.include("revenueAccount", revenueAccount);
		result.of(this).account();
	}

}
