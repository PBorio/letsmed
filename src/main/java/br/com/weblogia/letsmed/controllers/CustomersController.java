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
import br.com.weblogia.letsmed.domain.Customer;
import br.com.weblogia.letsmed.domain.Office;
import br.com.weblogia.letsmed.domain.Partner;
import br.com.weblogia.letsmed.domain.RevenueAccount;

@Controller
public class CustomersController {

	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@SuppressWarnings("unchecked")
	public void customer(){
		List<RevenueAccount> revenueAccountList = (List<RevenueAccount>) entityManager.createQuery(" from RevenueAccount ea order by ea.description ").getResultList();
		List<Office> officeList = (List<Office>) entityManager.createQuery(" from Office o order by o.officeName ").getResultList();
		List<Partner> partnerList = (List<Partner>) entityManager.createQuery(" from Partner p order by p.partnerName ").getResultList();
		result.include("revenueAccountList", revenueAccountList);
		result.include("officeList", officeList);
		result.include("partnerList", partnerList);
		result.include("controller", "customers");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/customers")
	public List<Customer> list(){
		result.include("controller", "customers");
		return (List<Customer>) entityManager.createQuery(" from Customer c order by c.name ").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void save(Customer customer){
		
		validator.addIf(( customer.getName() == null || customer.getName().trim().equals("")),new I18nMessage("cus","customer.without.name"));

		if(validator.hasErrors()){
			List<Partner> partnerList = (List<Partner>) entityManager.createQuery(" from Partner p order by p.partnerName ").getResultList();
			result.include("partnerList", partnerList);
			result.include("customer", customer);
			validator.onErrorUsePageOf(this).customer();
		}
		
		
		if (customer.getPartner().getId() == -1)
			customer.setPartner(null);
		
		if (customer.getId() == null){
			createRevenue(customer);
			entityManager.persist(customer);
		}else{
			entityManager.merge(customer);
		}
		result.redirectTo(this).list();
	}
	
	private void createRevenue(Customer customer) {
		RevenueAccount account = new RevenueAccount();
		account.setDescription(customer.getName());
		entityManager.persist(account);
		customer.setRevenueAccount(account);
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/customers/{id}")
	public void edit(Long id) {
		Customer customer = entityManager.find(Customer.class, id);
		List<RevenueAccount> revenueAccountList = (List<RevenueAccount>) entityManager.createQuery(" from RevenueAccount ea order by ea.description ").getResultList();
		List<Office> officeList = (List<Office>) entityManager.createQuery(" from Office o order by o.officeName ").getResultList();
		List<Partner> partnerList = (List<Partner>) entityManager.createQuery(" from Partner p order by p.partnerName ").getResultList();
		result.include("revenueAccountList", revenueAccountList);
		result.include("officeList", officeList);
		result.include("partnerList", partnerList);
		result.include("customer", customer);
		result.include("controller", "customers");
		result.of(this).customer();
	}
	
}
